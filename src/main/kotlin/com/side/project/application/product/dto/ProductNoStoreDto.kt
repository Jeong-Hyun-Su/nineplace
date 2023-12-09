package com.side.project.application.product.dto

import com.side.project.common.code.status.ProductStatus

data class ProductNoStoreDto(
    var name: String?,
    var price: Long,
    var image_url: String?,
    var status: ProductStatus?,
)