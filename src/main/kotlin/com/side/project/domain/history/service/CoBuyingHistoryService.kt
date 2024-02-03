package com.side.project.domain.history.service

import com.side.project.domain.history.event.ByCoBuyingCreateHistory
import com.side.project.domain.history.mapper.CoBuyingHistoryMapper
import com.side.project.domain.history.repository.CoBuyingHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoBuyingHistoryService(
    private val coBuyingHistoryRepository: CoBuyingHistoryRepository
) {
    @Transactional
    fun save(request: ByCoBuyingCreateHistory) {
        coBuyingHistoryRepository.save(
            CoBuyingHistoryMapper.INSTANCE.of(request.coBuying)
        )
    }
}