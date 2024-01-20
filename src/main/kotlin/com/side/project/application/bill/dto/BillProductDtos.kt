package com.side.project.application.bill.dto

import com.side.project.application.product.dto.DetailOptDto
import com.side.project.application.product.dto.ProductGrpOptDto
import java.util.UUID

data class BillProductDto (
    var price: Long,
    var amount: Long,
    var bill: BillDto,
    var grpOpt: ProductGrpOptDto,
    var detailOpt: DetailOptDto,
)

data class BillProductRequest (
    val amount: Long,
    val grpOptId: UUID,
    val detailOptId: UUID
)