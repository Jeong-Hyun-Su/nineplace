package com.side.project.domain.discount.event

import com.fasterxml.jackson.annotation.JsonFormat
import com.side.project.domain.discount.controller.dto.DiscountSectionRequest
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID

data class ByCoBuyingCreateEvent (
    val coBuyingId: UUID,
    val discount: List<DiscountSectionRequest>,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val startTime: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val endTime: LocalDateTime,
)