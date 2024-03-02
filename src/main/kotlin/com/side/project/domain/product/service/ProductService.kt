package com.side.project.domain.product.service

import com.side.project.domain.category.repository.CategoryRepository
import com.side.project.domain.cobuying.entity.CoBuying
import com.side.project.domain.cobuying.event.ByProductDeleteEvent
import com.side.project.domain.cobuying.repository.CoBuyingRepository
import com.side.project.domain.product.controller.dto.ProductRequest
import com.side.project.domain.product.controller.dto.ProductDto
import com.side.project.domain.product.controller.dto.ProductNoStoreDto
import com.side.project.domain.product.controller.dto.ProductUpdateRequest
import com.side.project.domain.product.entity.Product
import com.side.project.domain.product.event.OptionCreateEvent
import com.side.project.domain.product.mapper.ProductMapper
import com.side.project.domain.product.repository.ProductRepository
import com.side.project.domain.store.repository.StoreRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class ProductService(
    private val publisher: ApplicationEventPublisher,
    private val productRepository: ProductRepository,
    private val coBuyingRepository: CoBuyingRepository,
    private val storeRepository: StoreRepository,
    private val categoryRepository: CategoryRepository
) {
    fun getById(productId: UUID): ProductDto {
        return productRepository.getByIds(productId)
            .let(ProductMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(request: ProductRequest) {
        check(storeRepository.existsById(request.storeId)){ "가게가 존재하지 않습니다." }
        check(categoryRepository.existsById(request.categoryId)){ "카테고리가 존재하지 않습니다." }

        // 상품(Product) 생성
        val product = Product.create(request)
        productRepository.save(product)
        // 옵션 생성(이벤트)
        publisher.publishEvent(OptionCreateEvent(product, request.options))
    }

    @Transactional
    fun update(id: UUID, request: ProductUpdateRequest) {
        check(productRepository.existsById(id)){ "수정할 상품이 없습니다." }

        val product: Product = productRepository.getByIds(id)
        // 공동구매 등록된 건이 있으면, 조회
        if(product.existsCoBuying()) {
            val coBuying: CoBuying = coBuyingRepository.getByIds(product.coBuyingId!!)
            coBuying.isInProgress( "진행중인 공동구매건이 있어, 수정이 불가능합니다." )
        }
        // 등록 건이 없거나, 공동구매 진행건이 없으면 상품 업데이트
        product.update(request)
    }

    @Transactional
    fun delete(id: UUID) {
        check(productRepository.existsById(id)){ "삭제할 상품정보가 없습니다." }

        val product: Product = productRepository.getByIds(id)
        // 공동구매 등록된 건이 있으면, 조회
        if(product.existsCoBuying()) {
            val coBuying: CoBuying = coBuyingRepository.getByIds(product.coBuyingId!!)
            coBuying.isInProgress( "진행중인 공동구매건이 있어, 수정이 불가능합니다." )
        }
        // 상품 삭제(상태값)
        product.delete()
        // 공동구매 삭제(상태값) - 해당 상품으로 등록된 공동구매
        publisher.publishEvent(ByProductDeleteEvent(productId = product.id))
    }
}