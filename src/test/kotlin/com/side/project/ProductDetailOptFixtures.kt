package com.side.project

import com.side.project.domain.product.option.ProductDetailOpt
import com.side.project.domain.product.option.ProductGrpOpt

fun createProductDetailOpt(
    name: String = "상세옵션 1",
    price: Long = 5000L,
    grpOpt: ProductGrpOpt? = null,
): ProductDetailOpt {
    return ProductDetailOpt(
        name = name,
        price = price,
        grpOpt = grpOpt
    )
}