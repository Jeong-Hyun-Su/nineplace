package com.side.project.domain.discount.event

import java.util.UUID

data class ByCoBuyingDeleteEvent (
    val coBuyingId: UUID
)