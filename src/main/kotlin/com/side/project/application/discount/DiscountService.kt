package com.side.project.application.discount

import com.side.project.application.discount.dto.DiscountDto
import com.side.project.application.discount.dto.DiscountIdsDto
import com.side.project.common.code.status.DiscountStatus
import com.side.project.common.code.type.DiscountType
import com.side.project.domain.discount.Discount
import com.side.project.domain.discount.DiscountMapper
import com.side.project.domain.discount.DiscountRepository
import com.side.project.domain.discount.getByIds
import com.side.project.domain.discount.strategy.DiscountFactory
import com.side.project.domain.order.Order
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.min

@Service
@Transactional(readOnly = true)
class DiscountService(
    private val discountRepository: DiscountRepository,
) {
    fun getById(id: Long): DiscountDto {
        return discountRepository.getByIds(id)
                                 .let(DiscountMapper.INSTANCE::toDto)
    }
    fun getPercentFromOrder(order: Order): Long {
        val orderDiscount: List<Discount> = order.discount

        // 상품에 적용되어 있는 할인전략들 적용
        val discountStrategyList = DiscountFactory.createList(orderDiscount)
        return discountStrategyList.map { it?.calculate(order) ?: throw Exception("상품 할인 오류")  }
                                   .sumOf { it }
    }
    fun getPercentFromUser(discountIds: List<DiscountIdsDto>?): Long {
        if (discountIds == null) return 0L

        val discountList: List<Discount> = discountRepository.findDiscountsByIds(discountIds.map{ it.id })
        return discountList.filter { it.status == DiscountStatus.ACTIVATE && it.type != DiscountType.SECTION }
                           .sumOf { it.percent }
    }
    fun getFinalPercent(order: Order, discountIds: List<DiscountIdsDto>?): Long {
        // Select Discount List - 쿠폰 제외, 시작기간~종료기간, 활성화
        var percent = 0L

        percent += getPercentFromOrder(order)
        percent += getPercentFromUser(discountIds)
        // 제품의 최대할인이 넘지 않도록 조정
        return min(order.discountLimit, percent)
    }
    fun calculatePrice(price: Long, percent: Long): Long {
        return (price - (price * 0.01 * percent)).toLong()
    }

    @Transactional
    fun activateSection(order: Order) {
        val orderDiscount: List<Discount> = order.discount
        // SECTION 타입이고, 활성화되지 않은 섹션은 인원 충족시 활성화
        val discount: Discount? = orderDiscount.filter {
            it.type == DiscountType.SECTION &&
            it.status == DiscountStatus.DEACTIVATE &&
            (order.clientCount - it.clientSection) >= 0
        }.maxByOrNull { order.clientCount }

        discount?.status = DiscountStatus.ACTIVATE
    }
}