package com.side.project.domain.cobuying.listener

import com.side.project.domain.cobuying.event.ByProductDeleteEvent
import com.side.project.domain.cobuying.service.CoBuyingService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoBuyingByProductListener(
    private val coBuyingService: CoBuyingService
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun deleteCoBuying(request: ByProductDeleteEvent) {
        coBuyingService.deleteByProductId(productId = request.productId)
    }
}