package com.side.project.application.order.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.side.project.application.discount.dto.DiscountInProductRequest
import com.side.project.application.discount.dto.DiscountInProductUpdateRequest
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.common.code.status.OrderStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto (
    val title: String,
    val content: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val clientLimit: Long,
    val clientCount: Long,
    val viewCount: Long,
    val status: OrderStatus?,
    val product: ProductNoStoreDto,
)

data class OrderRequest (
    val productId: UUID,
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

data class OrderUpdateRequest (
    val title: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val startTime: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val endTime: LocalDateTime,

    val clientLimit: Long,
    val status: OrderStatus,

    val discount: List<DiscountInProductUpdateRequest>
)