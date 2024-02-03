package com.side.project.domain.discount.strategy

import com.side.project.domain.discount.entity.Discount
import com.side.project.domain.cobuying.entity.CoBuying

class ClientSectionDiscountStrategy(
    private val discount: Discount
): DiscountStrategy {
    override fun calculate(coBuying: CoBuying): Long {
        /*if(coBuying.userCount >= discount.userSection)
            return discount.percent
*/
        return 0
    }
}