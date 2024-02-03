package com.side.project.domain.cobuying.controller

import com.side.project.domain.cobuying.service.CoBuyingService
import com.side.project.domain.cobuying.controller.dto.CoBuyingRequest
import com.side.project.domain.cobuying.controller.dto.CoBuyingDto
import com.side.project.domain.cobuying.controller.dto.CoBuyingUpdateRequest
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cobuying")
class CoBuyingController(
    private val coBuyingService: CoBuyingService
){
    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: UUID): ApiResponse<CoBuyingDto> {
        val order = coBuyingService.getById(id)

        return ApiResponse.ok(message = "공동구매 조회 완료", data = order)
    }

    @PostMapping
    fun createOrder(@RequestBody coBuyingRequest: CoBuyingRequest): ApiResponse<Nothing> {
        coBuyingService.create(coBuyingRequest)

        return ApiResponse.created(message = "공동구매 생성 완료")
    }

    @PatchMapping("/{id}")
    fun updateOrder(@PathVariable id: UUID, @RequestBody coBuyingUpdateRequest: CoBuyingUpdateRequest): ApiResponse<Nothing> {
        coBuyingService.update(id, coBuyingUpdateRequest)

        return ApiResponse.ok(message = "공동구매 업데이트 완료")
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: UUID): ApiResponse<Nothing> {
        coBuyingService.delete(id)

        return ApiResponse.ok(message = "공동구매 삭제 완료")
    }
}