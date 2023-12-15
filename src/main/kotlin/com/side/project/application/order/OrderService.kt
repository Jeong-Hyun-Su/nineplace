package com.side.project.application.order

import com.side.project.application.order.dto.OrderRequest
import com.side.project.application.order.dto.OrderDto
import com.side.project.application.order.dto.OrderUpdateRequest
import com.side.project.common.code.status.DiscountStatus
import com.side.project.common.code.type.DiscountType
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
            discount.forEach { it.status = DiscountStatus.DEACTIVATE }
            // 구간할인 최대 3개
            check(discount.filter { it.type == DiscountType.SECTION }.size <= 3){ "구간 할인은 3개 이하만 가능합니다." }

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

    @Transactional
    fun update(id: Long, orderUpdateRequest: OrderUpdateRequest) {
        check(orderRepository.existsById(id)){ "수정할 주문건이 없습니다." }
        val order = orderRepository.getByIds(id)
        // 공동구매 진행중이면, 수정 불가
        check(order.status != OrderStatus.OPENED){ "현재 공동구매가 진행중이므로, 수정할 수 없습니다." }

        val discounts = order.discount
        val discountRemoveList: MutableList<Discount> = mutableListOf()
        // 할인정보 업데이트
        for(discount in discounts) {
            // 전달받은 할인정보와 등록된 할인정보가 일치하는 건이 있으면, 업데이트
            val uptDiscount = orderUpdateRequest.discount.filter { it.id == discount.id }.getOrNull(0) ?: continue
            // 할인정보 삭제로 변경 시, 데이터 삭제
            if(uptDiscount.status == DiscountStatus.REMOVE) {
                discountRepository.delete(discount)
                discountRemoveList.add(discount)
            } else {
                discount.update(uptDiscount)
            }
        }
        // 공동구매 업데이트
        order.update(orderUpdateRequest)
        order.discount.removeAll(discountRemoveList)    //연관관계 제거
    }

    @Transactional
    fun delete(id: Long) {
        check(orderRepository.existsById(id)){ "삭제할 주문건이 없습니다." }
        val order = orderRepository.getByIds(id)
        // 공동구매 진행중이면, 수정 불가
        check(order.status != OrderStatus.OPENED){ "현재 공동구매가 진행중이므로, 삭제할 수 없습니다." }

        // 할인정보 "삭제" 상태로 변경(히스토리)
        val discounts = order.discount
        discounts.forEach { it.delete() }
        // 공동구매 "삭제" 상태로 변경(히스토리)
        order.delete()
    }
}