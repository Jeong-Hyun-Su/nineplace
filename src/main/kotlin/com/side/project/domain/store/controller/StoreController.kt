package com.side.project.domain.store.controller

import com.side.project.domain.store.controller.dto.StoreDto
import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.store.service.StoreService
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/stores")
class StoreController(
    private val storeService: StoreService
) {
    @GetMapping("/{id}")
    fun getStore(@PathVariable id: UUID): ApiResponse<StoreDto> {
        val store = storeService.getById(id)

        return ApiResponse.ok(message = "스토어 조회 성공", data = store)
    }

    @PostMapping
    fun create(@RequestBody storeRequest: StoreRequest): ApiResponse<Nothing> {
        storeService.create(storeRequest)

        return ApiResponse.created(message = "스토어 생성 완료")
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody storeRequest: StoreRequest): ApiResponse<Nothing> {
        storeService.update(id, storeRequest)

        return ApiResponse.ok(message = "스토어 정보 업데이트 완료")
    }
}