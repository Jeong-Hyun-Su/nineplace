package com.side.project.application.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillDto
import com.side.project.domain.bill.Bill
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
    private val orderRepository: OrderRepository,
    private val billProductService: BillProductService,
) {
    fun getById(id: Long): BillDto {
        return billRepository.getByIds(id)
                             .let(BillMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(billCreateDto: BillCreateDto) {
        val order = orderRepository.getByIds(billCreateDto.orderId)

        // 주문 인원 체크 후 증가
        check( order.clientCount + 1 <= order.clientLimit ){ "인원이 꽉 찼습니다." }
        order.increaseClientCount()
        // Bill 생성
        val bill: Bill = billRepository.save(Bill(title = billCreateDto.title, price = 0, order = order))
        // 주문한 상품(BillProduct) 저장 및 최종가격 책정
        val price = billProductService.createListAndReturnTotalPrice(billCreateDto.billProduct, bill)
        // Bill 최종가격 책정
        bill.price = price
    }

    @Transactional
    fun delete(id: Long) {
        check(billRepository.existsById(id)){ "삭제할 주문이 없습니다." }

        billRepository.deleteById(id)
    }
}