package com.side.project

import com.side.project.domain.bill.Bill
import com.side.project.domain.bill.BillProduct
import com.side.project.domain.product.option.ProductDetailOpt
import com.side.project.domain.product.option.ProductGrpOpt

fun createBillProduct(
    price: Long = 50000,
    amount: Long = 4,
    bill: Bill = createBill(),
    grpOpt: ProductGrpOpt = createProductGrpOpt(),
    detailOpt: ProductDetailOpt = createProductDetailOpt(),
): BillProduct {
    return BillProduct(price, amount, bill, grpOpt, detailOpt)
}