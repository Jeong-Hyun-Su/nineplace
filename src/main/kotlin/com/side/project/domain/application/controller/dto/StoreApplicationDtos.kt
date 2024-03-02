package com.side.project.domain.application.controller.dto

import com.side.project.global.common.code.status.StoreStatus
import java.time.LocalDate
import java.util.*

data class StoreApplicationDto (
    val storeId: UUID,
    val name: String? = null,
    val introComment: String? = null,
    val imageUrl: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val businessNumber: String? = null,
    val openDate: LocalDate? = null,
    val status: StoreStatus? = null,
    val approval: Boolean = false
)