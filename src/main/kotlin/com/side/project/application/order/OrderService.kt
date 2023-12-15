package com.side.project.application.order

import com.side.project.application.order.dto.OrderRequest
import com.side.project.application.order.dto.OrderDto
import com.side.project.common.code.status.OrderStatus
import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper
import com.side.project.domain.discount.DiscountRepository
import com.side.project.domain.order.OrderMapper
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val discountRepository: DiscountRepository
){
    fun getById(orderId: Long): OrderDto {
        return orderRepository.getByIds(orderId)
                              .let(OrderMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(orderRequest: OrderRequest) {
        // 상품 조회
        val product = productRepository.getByIds(orderRequest.productId)
        // 할인정보 조회
        val discountsRequest = orderRequest.discount
        var discount: MutableList<Discount> = mutableListOf()

        // 할인정보 존재 시, 할인정보 생성
        if(discountsRequest.isNotEmpty()) {
            discount = discountsRequest.map(DiscountMapper.INSTANCE::ofDiscountInProduct).toMutableList()
            discount.forEach { it.status = false }
            discountRepository.saveAll(discount)
        }

        // 주문 저장
        val order = orderRepository.save(
            orderRequest.let(OrderMapper.INSTANCE::ofOrder)
                        .apply {
                              this.content = product.content
                              this.viewCount = 0
                              this.clientCount = 0
                              this.price = product.price
                              this.product = product
                              this.status = OrderStatus.CLOSED
                              this.discount = discount
                        }
        )
        // 할인정보 연관관계 설정
        discount.forEach{ it.order = order }
    }
}