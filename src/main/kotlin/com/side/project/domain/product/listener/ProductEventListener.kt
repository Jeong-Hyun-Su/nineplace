package com.side.project.domain.product.listener

import com.side.project.domain.product.event.OptionCreateEvent
import com.side.project.domain.product.service.ProductOptionService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductEventListener(
    private val productOptionService: ProductOptionService,
) {
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun createOption(optionCreateEvent: OptionCreateEvent) {
        val product = optionCreateEvent.product
        val options = optionCreateEvent.options

        productOptionService.create(options, product)
    }
}