package com.side.project.application.order.dto

import com.side.project.application.product.dto.ProductNoStoreDto
import java.time.LocalDateTime

data class OrderDto (
    var title: String,
    var content: String,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
    var clientMax: Long,
    var clientCount: Long,
    var viewCount: Long,
    var status: Boolean,
    var product: ProductNoStoreDto,
)