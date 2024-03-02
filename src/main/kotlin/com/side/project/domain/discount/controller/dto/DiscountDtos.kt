package com.side.project.domain.discount.controller.dto

import com.side.project.domain.cobuying.controller.dto.CoBuyingDto
import com.side.project.global.common.code.status.DiscountStatus
import com.side.project.global.common.code.type.DiscountType
import java.time.LocalDateTime
import java.util.UUID

data class DiscountDto (
    val name: String,
    val type: DiscountType,
    val percent: Long,
    val userSection: Long,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val status: DiscountStatus?,
    val order: CoBuyingDto?,
)

data class DiscountSectionDto (
    val id: UUID?,
    val status: DiscountStatus?,
    val name: String,
    val percent: Long,
    val userSection: Long,
)

data class DiscountSectionRequest (
    val id: UUID?,
    val status: DiscountStatus?,
    val name: String,
    val percent: Long,
    val userSection: Long,
)

data class CouponDiscountsDto (
    val id: UUID
)