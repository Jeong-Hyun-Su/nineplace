package com.side.project

import com.side.project.common.code.discount.DiscountType
import com.side.project.domain.discount.Discount
import com.side.project.domain.order.Order
import java.time.LocalDateTime

fun createDiscount(
    name: String = "할인정보",
    type: DiscountType = DiscountType.SECTION,
    percent: Long = 10L,
    clientSection: Long = 10L,
    amount: Long = 1L,
    limitAmount: Long = 3L,
    duplicateAmount: Long = 1L,
    startDate: LocalDateTime? = null,
    endDate: LocalDateTime? = null,
    status: Boolean = true,
    order: Order? = null
): Discount {
    return Discount(name = name,
        type = type,
        percent = percent,
        clientSection = clientSection,
        amount = amount,
        limitAmount = limitAmount,
        duplicateAmount = duplicateAmount,
        startDate = startDate,
        endDate = endDate,
        status = status,
        order = order)
}