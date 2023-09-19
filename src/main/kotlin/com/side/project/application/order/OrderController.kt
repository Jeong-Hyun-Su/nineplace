package com.side.project.application.order

import com.side.project.application.order.dto.OrderCreateDto
import com.side.project.application.order.dto.OrderDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
){
    @GetMapping("/{id}")
    fun orderGet(@PathVariable id: Long): ApiResponse<OrderDto> {
        val order = orderService.getById(id)

        return ApiResponse.ok(message = "조회 완료", data = order)
    }

    @PostMapping
    fun orderCreate(@RequestBody orderCreateDto: OrderCreateDto): ApiResponse<Nothing> {
        orderService.create(orderCreateDto)

        return ApiResponse.created(message = "주문 생성 완료")
    }
}