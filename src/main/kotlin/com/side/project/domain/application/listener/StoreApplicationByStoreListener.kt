package com.side.project.domain.application.listener

import com.side.project.domain.application.event.ByStoreCreateEvent
import com.side.project.domain.application.service.StoreApplicationService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class StoreApplicationByStoreListener(
    private val storeApplicationService: StoreApplicationService
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun create(request: ByStoreCreateEvent) {
        storeApplicationService.create(
            storeId = request.storeId,
            storeRequest = request.storeRequest
        )
    }
}