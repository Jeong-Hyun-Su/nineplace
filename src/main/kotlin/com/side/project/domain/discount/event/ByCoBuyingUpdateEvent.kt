package com.side.project.domain.discount.event

import com.side.project.domain.discount.controller.dto.DiscountSectionDto
import java.util.UUID

data class ByCoBuyingUpdateEvent(
    val coBuyingId: UUID,
    val discount: List<DiscountSectionDto>
)