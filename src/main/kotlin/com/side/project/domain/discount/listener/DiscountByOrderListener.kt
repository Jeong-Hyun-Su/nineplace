package com.side.project.domain.discount.listener

import com.side.project.domain.discount.event.ByOrderCreateEvent
import com.side.project.domain.discount.service.DiscountService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DiscountByOrderListener(
    private val discountService: DiscountService
) {
    @TransactionalEventListener
    fun activateSection(byOrderCreateEvent: ByOrderCreateEvent) {
        discountService.activateSection(byOrderCreateEvent.coBuyingId, byOrderCreateEvent.userCount)
    }
}