package com.side.project.application.product

import com.side.project.application.product.dto.ProductCreateDto
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.common.code.ProductStatus
import com.side.project.domain.category.CategoryRepository
import com.side.project.domain.category.DetailCategoryRepository
import com.side.project.domain.category.getByIds
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
    fun create(productCreateDto: ProductCreateDto) {
        // 스토어, 카테고리 존재하는지 확인
        val store = storeRepository.getByIds(productCreateDto.storeId)
        val category = categoryRepository.getByIds(productCreateDto.categoryId)
        val detailCategory = detailCategoryRepository.getByIds(productCreateDto.detailCategoryId)

        // 상품(Product) Entity
        productRepository.save(
            productCreateDto.let(ProductMapper.INSTANCE::ofProduct)
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
    fun delete(productId: Long) {
        check(productRepository.existsById(productId)){ "삭제할 상품정보가 없습니다." }

        productRepository.deleteById(productId)
    }
}