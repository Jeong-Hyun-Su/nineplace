package com.side.project.application.bill.dto

data class BillProductCreateDto (
    val amount: Long,
    val grpOptId: Long,
    val detailOptId: Long
)