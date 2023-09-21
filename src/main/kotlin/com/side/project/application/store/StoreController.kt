package com.side.project.application.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stores")
class StoreController(
    private val storeService: StoreService
) {
    @GetMapping("/{id}/all-info")
    fun getStore(@PathVariable id: Long): ApiResponse<StoreDto>{
        val store = storeService.getById(id)

        return ApiResponse.ok(message = "스토어 조회 성공", data = store)
    }

    @GetMapping("/{id}")
    fun getStoreNoProduct(@PathVariable id: Long): ApiResponse<StoreNoProductDto>{
        val store = storeService.getNoProductById(id)

        return ApiResponse.ok(message = "스토어 조회 성공", data = store)
    }
}