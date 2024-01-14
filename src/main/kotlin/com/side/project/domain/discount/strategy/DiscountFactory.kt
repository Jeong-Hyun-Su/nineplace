package com.side.project.domain.discount.strategy

import com.side.project.common.code.status.DiscountStatus
import com.side.project.common.code.type.DiscountType
import com.side.project.domain.discount.Discount

class DiscountFactory {
    companion object{
        // 할인전략 생성
        private fun create(discount: Discount?): DiscountStrategy? {
            return when(discount?.type) {
                DiscountType.SECTION -> ClientSectionDiscountStrategy(discount)
                DiscountType.PROMOTION -> PromotionDiscountStrategy(discount)
                DiscountType.COUPON -> CouponDiscountStrategy(discount)
                else -> null
            }
        }
        // 할인전략 리스트 생성(최종 섹션할인, 그 외)
        fun createList(discountList: List<Discount>): List<DiscountStrategy?> {
            val discountsNotSection: List<Discount> = discountList.filter { it.type != DiscountType.SECTION && it.status == DiscountStatus.ACTIVATE }
            val maxSection: Discount? = discountList.filter { it.type == DiscountType.SECTION && it.status == DiscountStatus.ACTIVATE }
                                                    .maxByOrNull { it.percent }

            return (discountsNotSection + maxSection).mapNotNull{ discount -> create(discount) }
        }
    }
}