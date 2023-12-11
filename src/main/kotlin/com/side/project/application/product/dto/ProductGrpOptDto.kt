package com.side.project.application.product.dto

data class ProductGrpOptDto (
    var id: Long,
    var name: String,
    var detailOpt: ArrayList<ProductDetailOptDto>?
)