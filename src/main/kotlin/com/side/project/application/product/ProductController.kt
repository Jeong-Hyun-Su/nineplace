package com.side.project.application.product

import com.side.project.application.product.dto.ProductRequest
import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.application.product.dto.ProductUpdateRequest
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/{id}/all-info")
    fun productAllInfo(@PathVariable id: Long): ApiResponse<ProductDto> {
        val product = productService.getById(id)

        return ApiResponse.ok(data = product)
    }

    @GetMapping("/{id}")
    fun productNoStore(@PathVariable id: Long): ApiResponse<ProductNoStoreDto> {
        val product = productService.getNoStoreById(id)

        return ApiResponse.ok(data = product)
    }

    @PostMapping
    fun productCreate(@RequestBody productRequest: ProductRequest): ApiResponse<ProductRequest>{
        productService.create(productRequest)

        return ApiResponse.ok(message = "상품 생성 완료", data = productRequest)
    }

    @PatchMapping("/{id}")
    fun productUpdate(@PathVariable id: Long, @RequestBody productUpdateRequest: ProductUpdateRequest): ApiResponse<Nothing> {
        productService.update(id, productUpdateRequest)

        return ApiResponse.ok(message = "상품 업데이트 완료")
    }

    @DeleteMapping("/{id}")
    fun productDelete(@PathVariable id: Long): ApiResponse<Nothing> {
        productService.delete(id)

        return ApiResponse.ok(message = "상품 삭제 완료")
    }
}