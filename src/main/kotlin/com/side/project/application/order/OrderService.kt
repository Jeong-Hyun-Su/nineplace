package com.side.project.application.order

import com.side.project.application.order.dto.OrderCreateDto
import com.side.project.application.order.dto.OrderDto
import com.side.project.domain.order.Order
import com.side.project.domain.order.OrderMapper
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import com.side.project.domain.product.ProductRepository
import com.side.project.domain.product.getByIds
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
){
    fun getById(orderId: Long): OrderDto {
        return orderRepository.getByIds(orderId)
                              .run(OrderMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(orderCreateDto: OrderCreateDto) {
        // 상품 조회
        val product = productRepository.getByIds(orderCreateDto.productId)

        // 주문 생성
        orderCreateDto.run(OrderMapper.INSTANCE::ofCreateEntity)
                      .apply {
                          this.content = product.content
                          this.viewCount = 0
                          this.clientCount = 0
                          this.product = product
                          orderRepository.save(this)
                      }
    }

    fun increaseClientCount(order: Order) {
        order.apply {
            this.clientCount += 1
            orderRepository.save(this)
        }
    }
}