package com.side.project.domain.product.event

import com.side.project.domain.product.controller.dto.ProductGroupOptDto
import com.side.project.domain.product.entity.Product

data class OptionCreateEvent (
    val product: Product,
    val options: List<ProductGroupOptDto>,
)