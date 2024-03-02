package com.side.project.domain.order.service

import com.side.project.domain.order.controller.dto.OrderRequest
import com.side.project.domain.order.controller.dto.OrderDto
import com.side.project.domain.cobuying.repository.CoBuyingRepository
import com.side.project.domain.discount.event.ByOrderCreateEvent
import com.side.project.domain.order.entity.Order
import com.side.project.domain.order.mapper.OrderMapper
import com.side.project.domain.order.repository.OrderRepository
import com.side.project.domain.order.repository.getByIds
import com.side.project.global.common.annotation.lock.DistributedLock
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class OrderService(
    private val publisher: ApplicationEventPublisher,
    private val orderRepository: OrderRepository,
    private val coBuyingRepository: CoBuyingRepository,
    private val orderCalculateService: OrderCalculateService
) {
    fun getById(id: UUID): OrderDto {
        return orderRepository.getByIds(id)
            .let(OrderMapper.INSTANCE::toOrderDto)
    }

    @DistributedLock(key = "#billRequest.orderId")
    fun create(orderRequest: OrderRequest) {
        val coBuying = coBuyingRepository.getByIds(orderRequest.coBuyingId)
        // 주문 가능여부 체크 (진행중 && 종료시간 && 인원)
        coBuying.isOrderAvailable()
        // 참여인원 증가 및 섹션할인 인원 충족 시, 활성화
        coBuying.increaseUserCount()
        // 섹션할인 구간 활성화/비활성화
        publisher.publishEvent(ByOrderCreateEvent(coBuying.id, coBuying.userCount))

        // 주문 생성
        val order: Order = orderRepository.save(
            OrderMapper.INSTANCE.of(orderRequest)
        )
        // 주문 품목 생성
        order.addLineItems(orderRequest.coBuyingLines)
        // 적용된 총 퍼센트 / 가격 책정
        order.calculatePercentAndPrice(orderCalculateService, orderRequest.couponDiscounts)
    }

    @Transactional
    fun delete(id: UUID) {
        check(orderRepository.existsById(id)){ "삭제할 주문이 없습니다." }

        orderRepository.deleteById(id)
    }
}