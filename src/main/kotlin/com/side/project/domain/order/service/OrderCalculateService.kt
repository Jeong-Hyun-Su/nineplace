package com.side.project.domain.order.service

import com.side.project.domain.discount.controller.dto.CouponDiscountsDto
import com.side.project.domain.discount.service.DiscountService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderCalculateService(
    private val discountService: DiscountService,
) {
    fun getAllPercent(coBuyingId: UUID, countDiscounts: List<CouponDiscountsDto>): Long {
        return discountService.getAllPercent(coBuyingId, countDiscounts)
    }
}