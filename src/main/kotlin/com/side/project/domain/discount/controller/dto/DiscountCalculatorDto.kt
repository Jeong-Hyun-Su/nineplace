package com.side.project.domain.discount.controller.dto

data class DiscountCalculatorDto(
    val discountList: List<DiscountDto>,
    val percent: Long = 0L,
    val price: Long = 0L
)