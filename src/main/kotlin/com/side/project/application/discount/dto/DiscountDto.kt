package com.side.project.application.discount.dto

import com.side.project.application.order.dto.OrderDto
import com.side.project.common.code.discount.DiscountType
import java.time.LocalDateTime

data class DiscountDto (
    var name: String,
    var type: DiscountType,
    var percent: Long,
    var clientSection: Long,
    var startDate: LocalDateTime?,
    var endDate: LocalDateTime?,
    var status: Boolean,
    var order: OrderDto?,
)