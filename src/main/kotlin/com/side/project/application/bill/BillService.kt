package com.side.project.application.bill

import com.side.project.application.bill.dto.BillRequest
import com.side.project.application.bill.dto.BillDto
import com.side.project.application.discount.DiscountService
import com.side.project.application.order.OrderService
import com.side.project.common.annotation.lock.DistributedLock
import com.side.project.common.code.status.OrderStatus
import com.side.project.domain.bill.Bill
import com.side.project.domain.bill.BillMapper
import com.side.project.domain.bill.getByIds
import com.side.project.domain.bill.BillRepository
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
@Transactional(readOnly = true)
class BillService(
    private val billRepository: BillRepository,
    private val orderRepository: OrderRepository,
    private val orderService: OrderService,
    private val billProductService: BillProductService,
    private val discountService: DiscountService,
) {
    fun getById(id: UUID): BillDto {
        return billRepository.getByIds(id)
                             .let(BillMapper.INSTANCE::toDto)
    }

    @DistributedLock(key = "#billRequest.orderId")
    fun create(billRequest: BillRequest) {
        val order = orderRepository.getByIds(billRequest.orderId)

        // 공동구매 진행중 && 종료시간 && 인원 체크
        check(order.status == OrderStatus.OPENED){ "공동구매가 종료되었습니다." }
        check(LocalDateTime.now().isBefore(order.endTime)){ "공동구매가 종료되었습니다." }
        check( order.clientCount + 1 <= order.clientLimit ){
            // 공동구매 CLOSED 상태 변경(독립적 트랜잭션)
            orderService.closedByMaxClient(order.id)
            "인원이 꽉 찼습니다. 공동구매가 종료되었습니다."
        }
        // 참여인원 증가 및 섹션할인 인원 충족 시, 활성화
        order.increaseClientCount()
        discountService.activateSection(order)

        // Bill(주문) 생성
        val bill: Bill = billRepository.save(
            billRequest.let(BillMapper.INSTANCE::of)
                       .apply { price = 0 }
        )

        // 주문한 상품(BillProduct) 생성 및 가격 계산
        val billProducts = billProductService.createAll(billRequest.billProduct, bill)
        val price = billProductService.getTotalPrice(billProducts)
        // 최종 책정된 할인율 계산
        val percent = discountService.getFinalPercent(order, billRequest.discountList)

        // Bill 최종 할인율 및 가격 책정
        bill.percent = percent
        bill.price = discountService.calculatePrice(price, percent)
    }

    @Transactional
    fun delete(id: UUID) {
        check(billRepository.existsById(id)){ "삭제할 주문이 없습니다." }

        billRepository.deleteById(id)
    }
}