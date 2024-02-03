package com.side.project.domain.order.controller.dto

import com.side.project.domain.cobuying.controller.dto.CoBuyingLineDto
import com.side.project.domain.discount.controller.dto.CouponDiscountsDto
import java.util.UUID

data class OrderDto (
    val title: String,
    val price: Long,
    val orderLines: List<OrderLineDto>
)

data class OrderRequest (
    val coBuyingId: UUID,
    val title: String,
    val coBuyingLines: List<CoBuyingLineDto>,
    val couponDiscounts: List<CouponDiscountsDto>,
)