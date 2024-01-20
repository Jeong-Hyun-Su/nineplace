package com.side.project.application.product.dto

import com.side.project.application.category.dto.CategoryNoDetailDto
import com.side.project.application.category.dto.DetailCategoryDto
import com.side.project.application.order.dto.OrderDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.common.code.status.ProductStatus
import java.util.UUID


data class ProductDto (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus?,
    val store: StoreNoProductDto?,
    val groupOpt: ArrayList<GroupOptDto?>,
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
    val storeId: UUID,
    val categoryId: UUID,
    val name: String,
    val content: String,
    val price: Long,
    val imageUrl: String?,
    val options: List<GroupOptDto>,
)

data class ProductUpdateRequest (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus
)