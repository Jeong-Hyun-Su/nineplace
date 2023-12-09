package com.side.project.domain.discount.strategy

import com.side.project.application.discount.dto.DiscountDto
import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper

class PromotionDiscountStrategy(
    val discount: Discount
): DiscountStrategy {
    override fun calculator(): DiscountDto {
        return discount.let(DiscountMapper.INSTANCE::toDto)
    }
}