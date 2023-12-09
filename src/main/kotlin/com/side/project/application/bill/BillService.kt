package com.side.project.application.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillDto
import com.side.project.domain.bill.BillMapper
import com.side.project.domain.bill.getByIds
import com.side.project.domain.bill.BillRepository
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BillService(
    private val billRepository: BillRepository,
    private val orderRepository: OrderRepository
) {
    fun getById(id: Long): BillDto {
        return billRepository.getByIds(id)
                             .let(BillMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(billCreateDto: BillCreateDto) {
        val order = orderRepository.getByIds(billCreateDto.orderId)
        // 주문 인원 체크 후 증가
        check(order.clientCount + 1 <= order.clientLimit){ "인원이 꽉 찼습니다." }
        order.increaseClientCount()
        // Bill 생성
        billRepository.save(
            billCreateDto.let(BillMapper.INSTANCE::ofCreateEntity)
                         .apply { this.order = order }
        )
    }
}