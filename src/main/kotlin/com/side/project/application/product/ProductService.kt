package com.side.project.application.product

import com.side.project.application.product.dto.ProductRequest
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.application.product.dto.ProductUpdateRequest
import com.side.project.domain.category.CategoryRepository
import com.side.project.domain.category.DetailCategoryRepository
import com.side.project.domain.product.*
import com.side.project.domain.product.option.ProductDetailOptRepository
import com.side.project.domain.store.StoreRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class ProductService(
    private val publisher: ApplicationEventPublisher,
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
    private val storeRepository: StoreRepository, private val productDetailOptRepository: ProductDetailOptRepository,
) {
    fun getById(productId: UUID): ProductDto {
        return productRepository.getByIds(productId)
                                .let(ProductMapper.INSTANCE::toDto)
    }

    fun getNoStoreById(productId: UUID): ProductNoStoreDto {
        return productRepository.getByIds(productId)
                                .let(ProductMapper.INSTANCE::toNoStoreDto)
    }

    @Transactional
    fun create(productRequest: ProductRequest) {
        //check(storeRepository.existsById(productRequest.storeId)){ "가게가 존재하지 않습니다." }
        //check(categoryRepository.existsById(productRequest.categoryId)){ "카테고리가 존재하지 않습니다." }

        // 상품(Product) Entity
        val product = Product.create(productRequest)
        productRepository.save(product)

        publisher.publishEvent(ProductCreateEvent(product, productRequest.options))
    }

    @Transactional
    fun update(id: UUID, productUpdateRequest: ProductUpdateRequest) {
        check(productRepository.existsById(id)){ "수정할 상품이 없습니다." }

        val product: Product = productRepository.getByIds(id)
        checkOrdersInProgress(product)
        // 상품 업데이트
        product.update(productUpdateRequest)
    }

    @Transactional
    fun delete(id: UUID) {
        check(productRepository.existsById(id)){ "삭제할 상품정보가 없습니다." }

        val product: Product = productRepository.getByIds(id)
        checkOrdersInProgress(product)
        // 상품 - "삭제", 공동구매 - "삭제" 상태 변경
        product.delete()
        //product.order.forEach { it.delete() }
    }

    fun checkOrdersInProgress(product: Product) {
        //val orders = product.order.filter { it.status == OrderStatus.OPENED }

        //require(orders.isEmpty()){ "진행되고 있는 공동구매건이 있습니다. 수정 불가합니다." }
    }
}