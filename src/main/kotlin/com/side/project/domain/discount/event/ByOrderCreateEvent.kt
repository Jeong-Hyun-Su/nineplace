package com.side.project.domain.discount.event

import com.side.project.domain.cobuying.entity.CoBuying
import java.util.UUID

data class ByOrderCreateEvent (
    val coBuyingId: UUID,
    val userCount: Long
)