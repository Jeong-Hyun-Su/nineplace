package com.side.project.application.bill.dto

import com.side.project.application.product.dto.ProductDetailOptDto
import com.side.project.application.product.dto.ProductGrpOptDto

data class BillProductDto (
    var price: Long,
    var amount: Long,
    var bill: BillDto,
    var grpOpt: ProductGrpOptDto,
    var detailOpt: ProductDetailOptDto,
)