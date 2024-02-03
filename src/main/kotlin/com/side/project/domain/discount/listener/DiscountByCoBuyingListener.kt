package com.side.project.domain.discount.listener

import com.side.project.domain.discount.event.ByCoBuyingCreateEvent
import com.side.project.domain.discount.event.ByCoBuyingDeleteEvent
import com.side.project.domain.discount.event.ByCoBuyingUpdateEvent
import com.side.project.domain.discount.service.DiscountService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DiscountByCoBuyingListener(
    private val discountService: DiscountService
) {
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun createSectionDiscount(request: ByCoBuyingCreateEvent) {
        discountService.createSectionByCoBuying(
            request.coBuyingId,
            request.discount,
            request.startTime,
            request.endTime
        )
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun updateSectionDiscount(request: ByCoBuyingUpdateEvent) {
        discountService.updateSectionByCoBuying(request.coBuyingId, request.discount)
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun deleteSectionDiscount(request: ByCoBuyingDeleteEvent) {
        discountService.deleteSectionByCoBuying(request.coBuyingId)
    }
}