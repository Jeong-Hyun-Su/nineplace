package com.side.project.application.product.dto

import com.side.project.application.category.dto.CategoryNoDetailDto
import com.side.project.application.category.dto.DetailCategoryDto
import com.side.project.application.order.dto.OrderDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.common.code.status.ProductStatus


data class ProductDto (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus?,
    val store: StoreNoProductDto?,
    val grpOpt: ArrayList<ProductGrpOptDto?>,
    val category: CategoryNoDetailDto?,
    val detailCategory: DetailCategoryDto?,
    val order: ArrayList<OrderDto>?
)

data class ProductNoStoreDto(
    val name: String?,
    val price: Long,
    val imageUrl: String?,
    val status: ProductStatus?,
)

data class ProductRequest (
    val storeId: Long,
    val name: String,
    val content: String,
    val price: Long,
    val imageUrl: String?,
    val categoryId: Long,
    val detailCategoryId: Long,
    val grpOpt: ArrayList<ProductGrpOptDto>,
)

data class ProductUpdateRequest (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus
)