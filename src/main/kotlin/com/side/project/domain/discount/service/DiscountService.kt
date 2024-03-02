package com.side.project.domain.discount.service

import com.side.project.domain.discount.controller.dto.DiscountDto
import com.side.project.domain.discount.controller.dto.CouponDiscountsDto
import com.side.project.domain.discount.entity.Discount
import com.side.project.domain.discount.mapper.DiscountMapper
import com.side.project.domain.discount.repository.DiscountRepository
import com.side.project.domain.discount.controller.dto.DiscountSectionDto
import com.side.project.domain.discount.controller.dto.DiscountSectionRequest
import com.side.project.global.common.code.status.DiscountStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
@Transactional(readOnly = true)
class DiscountService(
    private val discountRepository: DiscountRepository,
) {
    fun getById(id: UUID): DiscountDto {
        return discountRepository.getByIds(id)
                                 .let(DiscountMapper.INSTANCE::toDto)
    }

    @Transactional
    fun createSectionByCoBuying(coBuyingId: UUID, discountSectionRequest: List<DiscountSectionRequest>, startDate: LocalDateTime, endDate: LocalDateTime) {
        check(discountSectionRequest.size <= 3){ "구간 할인은 3개 이하만 가능합니다." }

        discountRepository.saveAll(
            discountSectionRequest.map {
                Discount.createSection(coBuyingId, it, startDate, endDate)
            }
        )
    }

    @Transactional
    fun updateSectionByCoBuying(coBuyingId: UUID, discountSectionDtoList: List<DiscountSectionDto>) {
        val sectionDiscounts: List<Discount> = discountRepository.findSectionDiscountsByCoBuyingId(coBuyingId)

        for(discountSectionDto in discountSectionDtoList) {
            sectionDiscounts
                .firstOrNull { it.id == discountSectionDto.id }
                ?.let{
                   if(discountSectionDto.status == DiscountStatus.REMOVE) {
                       discountRepository.delete(it)
                   } else {
                       it.updateSection(discountSectionDto)
                   }
                }
        }
    }

    @Transactional
    fun deleteSectionByCoBuying(coBuyingId: UUID) {
        val sectionDiscounts: List<Discount> = discountRepository.findSectionDiscountsByCoBuyingId(coBuyingId)
        sectionDiscounts.forEach { it.delete() }
    }

    // 활성화된 PROMOTION, SECTION 자동적용 되는 할인정보
    fun getActivateAutoApplyDiscountsByCoBuying(coBuyingId: UUID): List<Discount> {
        return discountRepository.findActivateAutoApplyDiscountsByCoBuyingId(coBuyingId)
    }

    // 활성화된 PROMOTION, SECTION 자동적용 되는 할인정보 총 퍼센트
    fun getAllPercentByActivateAutoApplyDiscounts(coBuyingId: UUID): Long{
        val discounts = getActivateAutoApplyDiscountsByCoBuying(coBuyingId)
        return discounts.sumOf { it.percent }
    }

    // 활성화된 Coupon 사용(고객 선택 시)
    fun useCouponDiscounts(couponDiscounts: List<CouponDiscountsDto>): Long {
        val discountList: List<Discount> = discountRepository.findActivateCouponDiscountsByIds(couponDiscounts.map{ it.id })
        discountList.forEach(Discount::use)

        return discountList.sumOf { it.percent }
    }

    fun getAllPercent(coBuyingId: UUID, couponDiscounts: List<CouponDiscountsDto>): Long {
        var percent = 0L
        percent += getAllPercentByActivateAutoApplyDiscounts(coBuyingId)
        percent += useCouponDiscounts(couponDiscounts)
        return percent
    }

    @Transactional
    fun activateSection(coBuyingId: UUID, userCount: Long) {
        val sectionDiscounts = discountRepository.findSectionDiscountsByCoBuyingId(coBuyingId)
        val activate = sectionDiscounts.firstOrNull { it.status == DiscountStatus.ACTIVATE }
        val minBy: Discount = sectionDiscounts.filter { it.userSection!! <= userCount }.minBy { it.userSection!! }

        if(activate != minBy){
            activate?.deActivate()
            minBy.activate()
        }
    }
}