package com.side.project.application.discount.dto

import com.side.project.application.order.dto.OrderDto
import com.side.project.common.code.status.DiscountStatus
import com.side.project.common.code.type.DiscountType
import java.time.LocalDateTime

data class DiscountDto (
    val name: String,
    val type: DiscountType,
    val percent: Long,
    val clientSection: Long,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val status: DiscountStatus?,
    val order: OrderDto?,
)

data class DiscountInProductRequest (
    val name: String,
    val type: DiscountType,
    val percent: Long,
    val clientSection: Long,
)

data class DiscountInProductUpdateRequest (
    val id: Long,
    val name: String,
    val type: DiscountType,
    val percent: Long,
    val clientSection: Long,
    val status: DiscountStatus,
)