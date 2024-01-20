package com.side.project.application.category.dto

import java.util.UUID

data class DetailCategoryCreateDto (
    var name: String,
    val categoryId: UUID,
)