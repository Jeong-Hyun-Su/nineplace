package com.side.project.domain.discount.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.side.project.domain.discount.entity.Discount
import com.side.project.domain.discount.entity.QDiscount.discount
import com.side.project.global.common.code.status.DiscountStatus
import com.side.project.global.common.code.type.DiscountType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DiscountRepositoryImpl(
    private val discountJpaRepository: DiscountJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): DiscountRepository {
    override fun getByIds(id: UUID): Discount = discountJpaRepository.findByIdOrNull(id) ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")
    override fun findDiscountsByIds(ids: List<UUID>): List<Discount> = discountJpaRepository.findDiscountsByIds(ids)
    override fun findActivateCouponDiscountsByIds(ids: List<UUID>): List<Discount> {
        return jpaQueryFactory.selectFrom(discount)
            .where(discount.id.`in`(ids))
            .fetch()
    }

    // 공동구매ID에 해당하는 Section할인 리스트
    override fun findSectionDiscountsByCoBuyingId(id: UUID): List<Discount> {
        return jpaQueryFactory.selectFrom(discount)
            .where(discount.coBuyingId.eq(id), discount.type.eq(DiscountType.SECTION))
            .fetch()
    }

    override fun findActivateSectionDiscountByCoBuyingId(id: UUID): Discount {
        return jpaQueryFactory.selectFrom(discount)
            .where(
                discount.coBuyingId.eq(id),
                discount.type.eq(DiscountType.SECTION),
                discount.status.eq(DiscountStatus.ACTIVATE)
            )
            .fetchFirst()
    }

    override fun findActivateAutoApplyDiscountsByCoBuyingId(id: UUID): List<Discount> {
        return jpaQueryFactory.selectFrom(discount)
            .where(
                discount.coBuyingId.eq(id),
                discount.status.eq(DiscountStatus.ACTIVATE),
                discount.type.ne(DiscountType.COUPON)
            )
            .fetch()
    }

    override fun saveAll(discounts: List<Discount>): List<Discount> = discountJpaRepository.saveAll(discounts)
    override fun delete(discount: Discount) = discountJpaRepository.delete(discount)
}