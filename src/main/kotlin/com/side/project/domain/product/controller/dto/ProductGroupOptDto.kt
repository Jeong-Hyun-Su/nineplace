package com.side.project.domain.product.controller.dto

data class ProductGroupOptDto (
    val name: String,
    val detail: List<ProductDetailOptDto>
)