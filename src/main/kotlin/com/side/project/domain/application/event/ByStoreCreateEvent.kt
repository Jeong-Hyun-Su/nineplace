package com.side.project.domain.application.event

import com.side.project.domain.store.controller.dto.StoreRequest
import java.util.*

data class ByStoreCreateEvent (
    val storeId: UUID,
    val storeRequest: StoreRequest
)