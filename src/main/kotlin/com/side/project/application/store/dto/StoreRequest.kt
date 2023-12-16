package com.side.project.application.store.dto

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class StoreRequest (
    val name: String,
    val introComment: String,
    val imageUrl: String,
    val address: String,
    val phoneNumber: String,
    val businessNumber: String,
    val certificated: Boolean,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val openDate: LocalDateTime,
)