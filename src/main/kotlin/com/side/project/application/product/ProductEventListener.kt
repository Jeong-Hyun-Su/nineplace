package com.side.project.application.product

import com.side.project.domain.product.ProductCreateEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ProductEventListener(
    private val productOptionService: ProductOptionService,
) {
    @EventListener
    fun create(productCreateEvent: ProductCreateEvent) {
        val product = productCreateEvent.product
        val options = productCreateEvent.options

        productOptionService.create(options, product)
    }
}