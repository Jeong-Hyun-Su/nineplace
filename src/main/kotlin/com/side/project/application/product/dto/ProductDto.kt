package com.side.project.application.product.dto

import com.side.project.application.category.dto.CategoryDto
import com.side.project.application.category.dto.CategoryNoDetailDto
import com.side.project.application.category.dto.DetailCategoryDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.domain.category.DetailCategory

data class ProductDto (
    var name: String,
    var price: Long,
    var image_url: String?,
    var store: StoreNoProductDto?,
    var grpOpt: ArrayList<ProductGrpOptDto?>,
    var category: CategoryNoDetailDto?,
    var detailCategory: DetailCategoryDto?,
)