package com.side.project.domain.product

import com.side.project.application.product.dto.GroupOptDto

data class ProductCreateEvent (
    val product: Product,
    val options: List<GroupOptDto>,
)