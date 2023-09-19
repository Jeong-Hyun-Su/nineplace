package com.side.project.application.bill.dto

data class BillCreateDto (
    var orderId: Long,
    var title: String,
    var price: Long,
    var grpOpt: String,
    var detailOpt: String,
)