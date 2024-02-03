package com.side.project.domain.category.controller.dto

import java.util.UUID

data class DetailCategoryCreateDto (
    var name: String,
    val categoryId: UUID,
)