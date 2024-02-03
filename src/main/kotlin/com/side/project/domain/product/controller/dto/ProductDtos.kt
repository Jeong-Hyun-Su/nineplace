package com.side.project.domain.product.controller.dto

import com.side.project.domain.category.controller.dto.CategoryNoDetailDto
import com.side.project.domain.category.controller.dto.DetailCategoryDto
import com.side.project.domain.cobuying.controller.dto.CoBuyingDto
import com.side.project.domain.store.controller.dto.StoreNoProductDto
import com.side.project.global.common.code.status.ProductStatus
import java.util.UUID


data class ProductDto (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus?,
    val store: StoreNoProductDto?,
    val groupOpt: List<ProductGroupOptDto>,
    val category: CategoryNoDetailDto?,
    val detailCategory: DetailCategoryDto?,
    val order: List<CoBuyingDto>?
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
    val options: List<ProductGroupOptDto>,
)

data class ProductUpdateRequest (
    val name: String,
    val price: Long,
    val imageUrl: String?,
    val content: String,
    val status: ProductStatus
)