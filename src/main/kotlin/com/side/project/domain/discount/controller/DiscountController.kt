package com.side.project.domain.discount.controller

import com.side.project.domain.discount.controller.dto.DiscountDto
import com.side.project.domain.discount.service.DiscountService
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/discounts")
class DiscountController(
    private val discountService: DiscountService
){
    @GetMapping("/{id}")
    fun discountGet(@PathVariable id: UUID): ApiResponse<DiscountDto> {
        val discount = discountService.getById(id)

        return ApiResponse.ok(message = "할인정보 조회 완료", data =  discount)
    }
}