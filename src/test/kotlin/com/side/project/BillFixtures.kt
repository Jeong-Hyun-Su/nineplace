package com.side.project

import com.side.project.domain.bill.Bill
import com.side.project.domain.bill.BillProduct
import com.side.project.domain.order.Order

fun createBill(
    title: String = "가구 주문건",
    price: Long = 50000L,
    billProduct: List<BillProduct> = listOf(),
    order: Order = createOrder(),
    percent: Long = 10L,
): Bill{
    return Bill(
        title = title,
        price = price,
        billProduct = billProduct.toMutableList(),
        order = order,
        percent = percent
    )
}