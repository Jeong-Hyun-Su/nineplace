package com.side.project.domain.discount.strategy

import com.side.project.common.code.discount.DiscountType
import com.side.project.domain.discount.Discount

class DiscountFactory {
    companion object{
        fun create(discount: Discount): DiscountStrategy? {
            return when(discount.type) {
                DiscountType.SECTION -> ClientSectionDiscountStrategy(discount)
                DiscountType.PROMOTION -> PromotionDiscountStrategy(discount)
                DiscountType.COUPON -> CouponDiscountStrategy(discount)
                else -> null
            }
        }
    }
}