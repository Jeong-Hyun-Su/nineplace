package com.side.project.domain.discount.strategy

import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper
import com.side.project.domain.order.Order

class CouponDiscountStrategy(
    val discount: Discount
): DiscountStrategy {
    override fun calculator(order: Order): Long {
        return discount.percent
    }
}