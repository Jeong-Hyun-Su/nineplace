package com.side.project.application.product

import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {
    @GetMapping("/product/all-info/{id}")
    fun getProduct(@PathVariable id: Long): ProductDto {
        return productService.getById(id)
    }

    @GetMapping("/product/{id}")
    fun getProductNoStore(@PathVariable id: Long): ProductNoStoreDto {
        return productService.getNoStoreById(id)
    }
}