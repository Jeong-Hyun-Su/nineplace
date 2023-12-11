package com.side.project.application.bill.dto

data class BillProductCreateDto (
    var amount: Long,
    var grpOptId: Long,
    var detailOptId: Long
)