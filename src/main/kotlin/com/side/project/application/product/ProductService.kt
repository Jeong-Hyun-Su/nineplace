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
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
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
        val category = categoryRepository.getByIds(productCreateDto.categoryId)
        val detailCategory = detailCategoryRepository.getByIds(productCreateDto.detailCategoryId)

        // 상품(Product) 저장
        val product = ProductMapper.INSTANCE.ofCreateEntity(productCreateDto).apply {
            this.category = category
            this.detailCategory = detailCategory

            productRepository.save(this)
        }

        // 그룹옵션, 상세옵션 저장
        productCreateDto.grpOpt.forEach {
           productOptionService.saveGrpAndDetailOption(product, it)
        }
    }
}