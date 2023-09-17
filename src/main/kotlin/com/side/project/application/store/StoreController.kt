package com.side.project.application.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController(
    private val storeService: StoreService
) {
    @GetMapping("/store/all-info/{id}")
    fun getStore(@PathVariable id: Long): StoreDto{
        return storeService.getById(id)
    }

    @GetMapping("/store/{id}")
    fun getStoreNoProduct(@PathVariable id: Long): StoreNoProductDto{
        return storeService.getNoProductById(id)
    }
}