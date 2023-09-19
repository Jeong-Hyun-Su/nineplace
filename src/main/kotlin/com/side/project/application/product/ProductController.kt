package com.side.project.application.product

import com.side.project.application.product.dto.ProductCreateDto
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {
    @GetMapping("/products/{id}/all-info")
    fun ProductAllInfo(@PathVariable id: Long): ApiResponse<ProductDto> {
        val product = productService.getById(id)
        return ApiResponse.ok(data = product)
    }

    @GetMapping("/products/{id}")
    fun ProductNoStore(@PathVariable id: Long): ApiResponse<ProductNoStoreDto> {
        val product = productService.getNoStoreById(id)
        return ApiResponse.ok(data = product)
    }

    @PostMapping("/products/create")
    fun ProductCreate(@RequestBody productCreateDto: ProductCreateDto): ApiResponse<ProductCreateDto>{
        productService.create(productCreateDto)

        return ApiResponse.ok(data = productCreateDto)
    }
}