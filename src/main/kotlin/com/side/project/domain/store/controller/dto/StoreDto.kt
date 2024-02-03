package com.side.project.domain.store.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.side.project.domain.product.controller.dto.ProductDto
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class StoreDto(
    val name: String?,
    val product: ArrayList<ProductDto?>,
    val introComment: String?,
    val imageUrl: String?,
    val address: String?,
    val phoneNumber: String?,
    val businessNumber: String?,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    val openDate: LocalDate?,
)