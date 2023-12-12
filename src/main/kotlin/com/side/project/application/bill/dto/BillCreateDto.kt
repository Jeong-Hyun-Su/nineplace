package com.side.project.application.bill.dto

data class BillCreateDto (
    var orderId: Long,
    var title: String,
    var billProduct: List<BillProductCreateDto>?,
    val discountList: List<Long>?,
)