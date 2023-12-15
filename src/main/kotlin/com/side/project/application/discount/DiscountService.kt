package com.side.project.application.discount

import com.side.project.application.discount.dto.DiscountDto
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

    fun calculator(order: Order, price: Long, discountIdList: List<Long>?): Long {
        // Select Discount List - 쿠폰 제외, 시작기간~종료기간, 활성화
        val orderDiscount: List<Discount> = order.discount
        var percent = 0L

        // 상품에 적용되어 있는 할인전략들 적용
        val discountStrategyList = DiscountFactory.createList(orderDiscount)
        discountStrategyList.forEach { percent += it?.calculator(order) ?: throw Exception("상품 할인 오류") }

        //그 외 상품에 적용되지 않은 활성화된 할인방법(쿠폰 등) 적용(전달받은 항목)
        if( discountIdList != null ) {
            val discountList: List<Discount> = discountRepository.findDiscountsByIds(discountIdList)
            discountList.filter { it.type != DiscountType.SECTION }.forEach { percent += it.percent }
        }

        // 제품의 최대할인이 넘지 않도록 조정
        percent = min(order.discountLimit, percent)

        // 할인가격 계산
        return (price - (price * 0.01 * percent)).toLong()
    }
}