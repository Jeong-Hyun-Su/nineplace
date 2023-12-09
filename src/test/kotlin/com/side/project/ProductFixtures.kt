package com.side.project

import com.side.project.common.code.status.ProductStatus
import com.side.project.common.code.status.ProductStatusConverter
import com.side.project.domain.category.Category
import com.side.project.domain.category.DetailCategory
import com.side.project.domain.order.Order
import com.side.project.domain.product.Product
import com.side.project.domain.product.option.ProductGrpOpt
import com.side.project.domain.store.Store
import jakarta.persistence.*
import java.util.ArrayList

fun createProduct(
    name: String = "테스트 상품",
    price: Long = 35000L,
    image_url: String? = null,
    content: String = "컨텐츠",
    status: ProductStatus = ProductStatus.OPENED,
    store: Store? = null,
    grpOpt: MutableList<ProductGrpOpt>? = ArrayList(),
    category: Category? = null,
    detailCategory: DetailCategory? = null,
    order: MutableList<Order>? = ArrayList(),
): Product {
    return Product(
        name = name,
        price = price,
        image_url = image_url,
        content = content,
        status = status,
        store = store,
        grpOpt = grpOpt,
        category = category,
        detailCategory = detailCategory,
        order = order
    )
}