package com.side.project.application.discount

import com.side.project.application.discount.dto.DiscountDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/discounts")
class DiscountController(
    private val discountService: DiscountService
){
    @GetMapping("/{id}")
    fun discountGet(@PathVariable id: Long): ApiResponse<DiscountDto> {
        val discount = discountService.getById(id)
        discountService.calculator(4L)
        return ApiResponse.ok(message = "할인정보 조회 완료", data =  discount)
    }
}