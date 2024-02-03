package com.side.project.domain.cobuying.controller.dto

import java.util.UUID

data class CoBuyingLineDto (
    val coBuyingLineId: UUID,
    val price: Long,
    val quantity: Long
)
data class CoBuyingLineRequest (
    val grpOptId: UUID,
    val detailOptId: UUID,
    val quantityLimit: Long
)