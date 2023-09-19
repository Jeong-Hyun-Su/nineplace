package com.side.project.application.product

import com.side.project.application.product.dto.ProductCreateDto
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.domain.category.CategoryRepository
import com.side.project.domain.category.DetailCategoryRepository
import com.side.project.domain.category.getByIds
import com.side.project.domain.product.ProductMapper
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import com.side.project.domain.store.StoreRepository
import com.side.project.domain.store.getByIds
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
    private val storeRepository: StoreRepository,
    private val productOptionService: ProductOptionService,
) {
    fun getById(productId: Long): ProductDto {
        val product = productRepository.getByIds(productId)

        return ProductMapper.INSTANCE.toDto(product)
    }

    fun getNoStoreById(productId: Long): ProductNoStoreDto {
        val product = productRepository.getByIds(productId)

        return ProductMapper.INSTANCE.toNoStoreDto(product)
    }

    @Transactional
    fun create(productCreateDto: ProductCreateDto){
        // 스토어, 카테고리 존재하는지 확인
        val store = storeRepository.getByIds(productCreateDto.storeId)
        val category = categoryRepository.getByIds(productCreateDto.categoryId)
        val detailCategory = detailCategoryRepository.getByIds(productCreateDto.detailCategoryId)

        // 상품(Product) 저장
        val product = productCreateDto.run(ProductMapper.INSTANCE::ofCreateEntity)
                                      .apply {
                                          this.store = store
                                          this.category = category
                                          this.detailCategory = detailCategory
                                      }

        // 그룹옵션, 상세옵션 저장
        productCreateDto.grpOpt.forEach {
           productOptionService.saveGrpAndDetailOption(product, it)
        }
    }
}