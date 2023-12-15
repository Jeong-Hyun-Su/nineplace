package com.side.project.application.order

import com.side.project.application.order.dto.OrderRequest
import com.side.project.application.order.dto.OrderDto
import com.side.project.application.order.dto.OrderUpdateRequest
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.*

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
    fun orderCreate(@RequestBody orderRequest: OrderRequest): ApiResponse<Nothing> {
        orderService.create(orderRequest)

        return ApiResponse.created(message = "주문 생성 완료")
    }

    @PatchMapping("/{id}")
    fun orderUpdate(@PathVariable id: Long, @RequestBody orderUpdateRequest: OrderUpdateRequest): ApiResponse<Nothing> {
        orderService.update(id, orderUpdateRequest)

        return ApiResponse.ok(message = "주문 업데이트 완료")
    }

    @DeleteMapping("/{id}")
    fun orderDelete(@PathVariable id: Long): ApiResponse<Nothing> {
        orderService.delete(id)

        return ApiResponse.ok(message = "주문 삭제 완료")
    }
}