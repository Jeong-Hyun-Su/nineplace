package com.side.project.domain.cobuying.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.side.project.domain.discount.controller.dto.DiscountSectionDto
import com.side.project.domain.discount.controller.dto.DiscountSectionRequest
import com.side.project.domain.product.controller.dto.ProductNoStoreDto
import com.side.project.global.common.code.status.CoBuyingStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID

data class CoBuyingDto (
    val title: String,
    val content: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val userCapacity: Long,
    val userCount: Long,
    val viewCount: Long,
    val status: CoBuyingStatus?,
    val product: ProductNoStoreDto,
)

data class CoBuyingRequest (
    val productId: UUID,
    val title: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val startTime: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val endTime: LocalDateTime,

    val userCapacity: Long,
    val percentLimit: Long,

    val coBuyingLines: List<CoBuyingLineRequest>,
    val discounts: List<DiscountSectionRequest>,
)

data class CoBuyingUpdateRequest (
    val title: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val startTime: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val endTime: LocalDateTime,

    val userCapacity: Long,
    val status: CoBuyingStatus,

    val coBuyingLines: List<CoBuyingLineDto>,
    val discount: List<DiscountSectionDto>
)