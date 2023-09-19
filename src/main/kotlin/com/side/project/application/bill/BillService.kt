package com.side.project.application.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillDto
import com.side.project.application.order.OrderService
import com.side.project.domain.bill.BillMapper
import com.side.project.domain.bill.getByIds
import com.side.project.domain.bill.BillRepository
import com.side.project.domain.order.Order
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BillService(
    private val billRepository: BillRepository,
    private val orderRepository: OrderRepository,
    private val orderService: OrderService
) {
    fun getById(id: Long): BillDto {
        return billRepository.getByIds(id)
                             .run(BillMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(billCreateDto: BillCreateDto) {
        val order = orderRepository.getByIds(billCreateDto.orderId)

        // 주문 인원 체크
        check(order.clientCount + 1 <= order.clientMax){"인원이 꽉 찼습니다."}
        // 주문 인원 + 1
        orderService.increaseClientCount(order)
        // Bill 생성
        createBill(billCreateDto, order)
    }

    fun createBill(billCreateDto: BillCreateDto, order: Order) {
        billCreateDto.run(BillMapper.INSTANCE::ofCreateEntity)
                     .apply {
                         this.order = order
                         billRepository.save(this)
                     }
    }
}