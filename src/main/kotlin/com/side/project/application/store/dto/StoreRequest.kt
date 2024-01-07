package com.side.project.application.store.dto

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class StoreRequest (
    val name: String,
    val introComment: String,
    val imageUrl: String,
    val address: String,
    val phoneNumber: String,
    val businessNumber: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    val openDate: LocalDate,
)