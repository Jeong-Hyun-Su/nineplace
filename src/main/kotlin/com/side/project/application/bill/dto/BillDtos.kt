package com.side.project.application.bill.dto

import com.side.project.application.discount.dto.DiscountIdsDto
import java.util.UUID

data class BillDto (
    val title: String,
    val price: Long,
    val billProduct: List<BillProductDto>
)

data class BillRequest (
    val orderId: UUID,
    val title: String,
    val billProduct: List<BillProductRequest>,
    val discountList: List<DiscountIdsDto>?,
)