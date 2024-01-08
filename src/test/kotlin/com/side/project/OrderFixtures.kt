package com.side.project

import com.side.project.common.code.type.DiscountType
import com.side.project.common.code.status.OrderStatus
import com.side.project.domain.discount.Discount
import com.side.project.domain.order.Order
import com.side.project.domain.product.Product
import com.side.project.domain.store.Store
import java.time.LocalDateTime

fun createOrder(
    title: String = "가구 공동구매",
    content: String = "",
    price: Long = 35000L,
    discountLimit: Long = 35,
    startTime: LocalDateTime = LocalDateTime.now(),
    endTime: LocalDateTime = LocalDateTime.now(),
    clientLimit: Long = 50L,
    clientCount: Long = 23L,
    viewCount: Long = 138L,
    status: OrderStatus = OrderStatus.OPENED,
    discount: List<Discount> = listOf(createDiscount(name = "섹션 1구간", type = DiscountType.SECTION, clientSection = 10L),
                                      createDiscount(name = "섹션 2구간", type = DiscountType.SECTION, clientSection = 20L),
                                      createDiscount(name = "프로모션", type = DiscountType.PROMOTION, clientSection = 20L),),
    product: Product = createProduct(),
    store: Store = createStore()
): Order {
    return Order(title = title,
                 content = content,
                 price = price,
                 discountLimit = discountLimit,
                 startTime = startTime,
                 endTime = endTime,
                 clientLimit = clientLimit,
                 clientCount = clientCount,
                 viewCount = viewCount,
                 status = status,
                 discount = discount.toMutableList(),
                 product = product,
                 store = store)
}