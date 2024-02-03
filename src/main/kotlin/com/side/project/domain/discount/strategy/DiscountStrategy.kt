package com.side.project.domain.discount.strategy

import com.side.project.domain.cobuying.entity.CoBuying

interface DiscountStrategy {
    fun calculate(coBuying: CoBuying): Long
}