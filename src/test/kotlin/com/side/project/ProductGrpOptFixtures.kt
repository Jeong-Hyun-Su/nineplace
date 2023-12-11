package com.side.project

import com.side.project.domain.product.Product
import com.side.project.domain.product.option.ProductDetailOpt
import com.side.project.domain.product.option.ProductGrpOpt

fun createProductGrpOpt(
    name: String = "그룹옵션 1",
    product: Product? = createProduct(name = "목걸이"),
    detailOpt: MutableList<ProductDetailOpt> = listOf(
        createProductDetailOpt(name = "진주목걸이", price = 5000L),
        createProductDetailOpt(name = "금목걸이", price = 15000L),
        ).toMutableList(),
): ProductGrpOpt {
    return ProductGrpOpt(
        name = name,
        product = product,
        detailOpt = detailOpt
    )
}