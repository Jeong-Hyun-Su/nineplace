package com.side.project.domain.order.controller.dto

import com.side.project.domain.product.controller.dto.ProductDetailOptDto
import com.side.project.domain.product.controller.dto.ProductGroupOptDto
import java.util.UUID

data class OrderLineDto (
    var price: Long,
    var quantity: Long,
    var bill: OrderDto,
    var grpOpt: ProductGroupOptDto,
    var detailOpt: ProductDetailOptDto,
)

data class OrderLineRequest (
    val quantity: Long,
    val grpOptId: UUID,
    val detailOptId: UUID
)