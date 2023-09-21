package com.side.project.application.order

import com.side.project.application.order.dto.OrderCreateDto
import com.side.project.application.order.dto.OrderDto
import com.side.project.domain.order.Order
import com.side.project.domain.order.OrderMapper
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import com.side.project.domain.product.Product
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
){
    fun getById(orderId: Long): OrderDto {
        return orderRepository.getByIds(orderId)
                              .let(OrderMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(orderCreateDto: OrderCreateDto) {
        // 상품 조회
        val product = productRepository.getByIds(orderCreateDto.productId)
        // 주문 저장
        orderRepository.save(
            createOrderEntity(orderCreateDto, product)
        )
    }

    fun createOrderEntity(orderCreateDto: OrderCreateDto, product: Product): Order {
        return orderCreateDto.let(OrderMapper.INSTANCE::ofCreateEntity)
                             .apply {
                                 this.content = product.content
                                 this.viewCount = 0
                                 this.clientCount = 0
                                 this.product = product
                             }
    }
}