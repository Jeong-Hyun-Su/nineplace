package com.side.project.application.product

import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.domain.product.ProductMapper
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun getById(productId: Long): ProductDto {
        val product = productRepository.getByIds(productId)

        return ProductMapper.INSTANCE.toDto(product)
    }

    fun getNoStoreById(productId: Long): ProductNoStoreDto {
        val product = productRepository.getByIds(productId)

        return ProductMapper.INSTANCE.toNoStoreDto(product)
    }
}