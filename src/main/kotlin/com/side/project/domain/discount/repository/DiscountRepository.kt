package com.side.project.domain.discount.repository

import com.side.project.domain.discount.entity.Discount
import java.util.UUID


interface DiscountRepository {

    fun getByIds(id: UUID): Discount
    fun findDiscountsByIds(ids: List<UUID>): List<Discount>
    fun findActivateCouponDiscountsByIds(ids: List<UUID>): List<Discount>
    // 공동구매ID에 해당하는 Section할인 리스트
    fun findSectionDiscountsByCoBuyingId(id: UUID): List<Discount>
    fun findActivateSectionDiscountByCoBuyingId(id: UUID): Discount
    fun findActivateAutoApplyDiscountsByCoBuyingId(id: UUID): List<Discount>
    fun saveAll(discounts: List<Discount>): List<Discount>
    fun delete(discount: Discount)
}