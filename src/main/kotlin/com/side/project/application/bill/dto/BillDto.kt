package com.side.project.application.bill.dto

data class BillDto (
    var title: String,
    var price: Long,
    var billProduct: List<BillProductDto>
)