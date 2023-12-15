package com.side.project.application.product

import com.side.project.application.product.dto.ProductRequest
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.application.product.dto.ProductUpdateRequest
import com.side.project.common.code.status.OrderStatus
import com.side.project.common.code.status.ProductStatus
import com.side.project.domain.category.CategoryRepository
import com.side.project.domain.category.DetailCategoryRepository
import com.side.project.domain.category.getByIds
import com.side.project.domain.product.Product
import com.side.project.domain.product.ProductMapper
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import com.side.project.domain.store.StoreRepository
import com.side.project.domain.store.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
    private val storeRepository: StoreRepository,
) {
    fun getById(productId: Long): ProductDto {
        return productRepository.getByIds(productId)
                                .let(ProductMapper.INSTANCE::toDto)
    }

    fun getNoStoreById(productId: Long): ProductNoStoreDto {
        return productRepository.getByIds(productId)
                                .let(ProductMapper.INSTANCE::toNoStoreDto)
    }

    @Transactional
    fun create(productRequest: ProductRequest) {
        // 스토어, 카테고리 존재하는지 확인
        val store = storeRepository.getByIds(productRequest.storeId)
        val category = categoryRepository.getByIds(productRequest.categoryId)
        val detailCategory = detailCategoryRepository.getByIds(productRequest.detailCategoryId)

        // 상품(Product) Entity
        productRepository.save(
            productRequest.let(ProductMapper.INSTANCE::ofProduct)
                          .apply {
                                this.store = store
                                this.category = category
                                this.detailCategory = detailCategory
                                this.status = ProductStatus.OPENED

                                // 연관관계 설정( 그룹, 상세옵션 )
                                this.grpOpt?.forEach {
                                    it.detailOpt?.forEach { detailOpt -> detailOpt.grpOpt = it }
                                    it.product = this
                                }
                          }
        )
    }

    @Transactional
    fun update(id: Long, productUpdateRequest: ProductUpdateRequest) {
        check(productRepository.existsById(id)){ "수정할 상품이 없습니다." }

        val product: Product = productRepository.getByIds(id)
        checkOrdersInProgress(product)
        // 상품 업데이트
        product.update(productUpdateRequest)
    }

    @Transactional
    fun delete(id: Long) {
        check(productRepository.existsById(id)){ "삭제할 상품정보가 없습니다." }

        val product: Product = productRepository.getByIds(id)
        checkOrdersInProgress(product)
        // 상품 - "삭제", 공동구매 - "삭제" 상태 변경
        product.delete()
        product.order.forEach { it.delete() }
    }

    fun checkOrdersInProgress(product: Product) {
        val orders = product.order.filter { it.status == OrderStatus.OPENED }

        require(orders.isEmpty()){ "진행되고 있는 공동구매건이 있습니다. 수정 불가합니다." }
    }
}