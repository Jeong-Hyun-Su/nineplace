package com.side.project.application.store.dto

import com.side.project.application.product.dto.ProductNoStoreDto
import java.time.LocalDateTime

data class StoreDto(
    var name: String?,
    var product: ArrayList<ProductNoStoreDto?>,
    var introComment: String?,
    var imageUrl: String?,
    var address: String?,
    var phoneNumber: String?,
    var businessNumber: String?,
    var openDate: LocalDateTime?,
    var certificated: Boolean?,
)