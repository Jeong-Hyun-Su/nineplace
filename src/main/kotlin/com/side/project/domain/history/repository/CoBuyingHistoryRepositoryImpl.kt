package com.side.project.domain.history.repository

import com.side.project.domain.history.entity.CoBuyingHistory
import org.springframework.stereotype.Repository

@Repository
class CoBuyingHistoryRepositoryImpl(
    private val coBuyingHistoryJpaRepository: CoBuyingHistoryJpaRepository
): CoBuyingHistoryRepository {
    override fun save(coBuyingHistory: CoBuyingHistory): CoBuyingHistory =
        coBuyingHistoryJpaRepository.save(coBuyingHistory)
}