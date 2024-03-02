package com.side.project.domain.product.controller

import com.side.project.domain.product.controller.dto.ProductRequest
import com.side.project.domain.product.controller.dto.ProductDto
import com.side.project.domain.product.controller.dto.ProductNoStoreDto
import com.side.project.domain.product.controller.dto.ProductUpdateRequest
import com.side.project.domain.product.repository.ProductRepository
import com.side.project.domain.product.service.ProductService
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {
    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID): ApiResponse<ProductDto> {
        val product = productService.getById(id)

        return ApiResponse.ok(data = product)
    }

    @PostMapping
    fun create(@RequestBody request: ProductRequest): ApiResponse<ProductRequest>{
        productService.create(request)

        return ApiResponse.ok(message = "상품 생성 완료")
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: ProductUpdateRequest): ApiResponse<Nothing> {
        productService.update(id, request)

        return ApiResponse.ok(message = "상품 업데이트 완료")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ApiResponse<Nothing> {
        productService.delete(id)

        return ApiResponse.ok(message = "상품 삭제 완료")
    }
}