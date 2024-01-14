package com.side.project.domain.discount.strategy

import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper
import com.side.project.domain.order.Order

class ClientSectionDiscountStrategy(
    private val discount: Discount
): DiscountStrategy {
    override fun calculate(order: Order): Long {
        if(order.clientCount >= discount.clientSection)
            return discount.percent

        return 0
    }
}