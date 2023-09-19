package com.side.project.application.product.dto

data class ProductCreateDto (
    var storeId: Long,

    var name: String,
    var price: Long,
    var image_url: String?,

    var categoryId: Long,
    var detailCategoryId: Long,

    var grpOpt: ArrayList<ProductGrpOptDto>,
)