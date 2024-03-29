package com.side.project.domain.product.controller.dto

data class ProductGroupOptDto (
    val name: String,
    val detail: List<ProductDetailOptDto>
)

data class ProductDetailOptDto (
    val name: String,
    val price: Long,
)