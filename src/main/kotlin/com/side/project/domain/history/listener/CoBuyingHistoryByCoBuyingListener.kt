package com.side.project.domain.history.listener

import com.side.project.domain.history.event.ByCoBuyingCreateHistory
import com.side.project.domain.history.service.CoBuyingHistoryService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoBuyingHistoryByCoBuyingListener(
    private val coBuyingHistoryService: CoBuyingHistoryService
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun createCoBuyingHistory(request: ByCoBuyingCreateHistory) {
        coBuyingHistoryService.save(request)
    }
}