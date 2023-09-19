package com.side.project.application.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
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
    fun getStore(@PathVariable id: Long): StoreDto{
        return storeService.getById(id)
    }

    @GetMapping("/{id}")
    fun getStoreNoProduct(@PathVariable id: Long): StoreNoProductDto{
        return storeService.getNoProductById(id)
    }
}