package com.side.project.domain.discount.strategy

import com.side.project.domain.order.Order

interface DiscountStrategy {
    fun calculator(order: Order): Long
}