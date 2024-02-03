package com.side.project.domain.discount.strategy

import com.side.project.domain.discount.entity.Discount
import com.side.project.domain.cobuying.entity.CoBuying

class PromotionDiscountStrategy(
    val discount: Discount
): DiscountStrategy {
    override fun calculate(coBuying: CoBuying): Long {
        return discount.percent
    }
}