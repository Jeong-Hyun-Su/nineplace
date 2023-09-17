package com.side.project.application.product.dto

import com.side.project.application.store.dto.StoreNoProductDto

data class ProductDto (
    var name: String?,
    var price: Long,
    var image_url: String?,
    var store: StoreNoProductDto?
)