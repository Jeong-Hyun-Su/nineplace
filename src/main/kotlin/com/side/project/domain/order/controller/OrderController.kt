package com.side.project.domain.order.controller

import com.side.project.domain.order.controller.dto.OrderRequest
import com.side.project.domain.order.controller.dto.OrderDto
import com.side.project.domain.order.service.OrderService
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/bills")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping("/{id}")
    fun billGet(@PathVariable id: UUID): ApiResponse<OrderDto> {
        val bill = orderService.getById(id)

        return ApiResponse.ok(message = "Bill 조회 성공", data = bill)
    }

    @PostMapping
    fun billCreate(@RequestBody orderRequest: OrderRequest): ApiResponse<Nothing> {
        orderService.create(orderRequest)

        return ApiResponse.created(message = "Bill 생성 성공")
    }

    @DeleteMapping("/{id}")
    fun billDelete(@PathVariable id: UUID): ApiResponse<Nothing> {
        orderService.delete(id)

        return ApiResponse.ok(message = "Bill 삭제 성공")
    }
}