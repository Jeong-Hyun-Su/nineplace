package com.side.project.application.discount

import com.side.project.application.discount.dto.DiscountCalcDto
import com.side.project.application.discount.dto.DiscountDto
import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper
import com.side.project.domain.discount.DiscountRepository
import com.side.project.domain.discount.getByIds
import com.side.project.domain.discount.strategy.ClientSectionDiscountStrategy
import com.side.project.domain.discount.strategy.DiscountFactory
import com.side.project.domain.discount.strategy.DiscountStrategy
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.max
import kotlin.math.min

@Service
@Transactional(readOnly = true)
class DiscountService(
    private val discountRepository: DiscountRepository,
    private val orderRepository: OrderRepository,
) {
    fun getById(id: Long): DiscountDto {
        return discountRepository.getByIds(id)
                                 .let(DiscountMapper.INSTANCE::toDto)
    }

    fun calculator(orderId: Long): DiscountCalcDto {
        // Select Discount List - 쿠폰 제외, 시작기간~종료기간, 활성화
        val order = orderRepository.getByIds(orderId)

        val discountList: List<Discount> = order.discount
        var clientSectionPercent = 0L
        var percent = 0L

        // 할인정보 계산
        for(discount in discountList) {
            val discountStrategy: DiscountStrategy? = DiscountFactory.create(discount)
            val discountDto: DiscountDto = discountStrategy?.calculator() ?: throw Exception()

            // 섹션 할인
            if( discountStrategy is ClientSectionDiscountStrategy &&
                order.clientCount <= discount.clientSection )
            {
                clientSectionPercent = max(discountDto.clientSection, clientSectionPercent)
            } else {
                percent += discountDto.percent
            }
        }
        // 제품의 최대할인이 넘지 않도록 조정
        percent = min(order.discountLimit, percent + clientSectionPercent)
        // 할인가격 계산
        val price: Long = (order.price - (order.price * 0.01 * percent)).toLong()

        return DiscountCalcDto(discountList = discountList.map(DiscountMapper.INSTANCE::toDto).toList(),
                               percent = percent,
                               price = price)
    }
}