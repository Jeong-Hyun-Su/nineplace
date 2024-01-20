package com.side.project

import com.side.project.domain.product.option.ProductDetailOpt
import com.side.project.domain.product.option.ProductGroupOpt

fun createProductDetailOpt(
    name: String = "상세옵션 1",
    price: Long = 5000L,
    grpOpt: ProductGroupOpt? = null,
): ProductDetailOpt {
    return ProductDetailOpt(
        name = name,
        price = price,
        grpOpt = grpOpt
    )
}