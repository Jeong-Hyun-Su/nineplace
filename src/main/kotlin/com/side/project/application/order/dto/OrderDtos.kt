package com.side.project.application.order.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.side.project.application.discount.dto.DiscountInProductRequest
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.common.code.status.OrderStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class OrderDto (
    var title: String,
    var content: String,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
    var clientMax: Long,
    var clientCount: Long,
    var viewCount: Long,
    var status: OrderStatus?,
    var product: ProductNoStoreDto,
)

data class OrderRequest (
    val productId: Long,
    val title: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val startTime: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val endTime: LocalDateTime,

    val clientLimit: Long,

    val discount: List<DiscountInProductRequest>
)