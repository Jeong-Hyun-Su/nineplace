package com.side.project.application.store.dto

import com.side.project.application.product.dto.ProductNoStoreDto
import java.time.LocalDateTime

data class StoreDto(
    var name: String?,
    var product: ArrayList<ProductNoStoreDto?>,
    var intro_comment: String?,
    var image_url: String?,
    var address: String?,
    var phone_number: String?,
    var business_number: String?,
    var open_date: LocalDateTime?,
    var certificated_yn: Boolean?,
)