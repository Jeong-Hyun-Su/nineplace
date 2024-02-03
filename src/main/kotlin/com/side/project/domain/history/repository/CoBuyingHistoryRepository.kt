package com.side.project.domain.history.repository

import com.side.project.domain.history.entity.CoBuyingHistory

interface CoBuyingHistoryRepository {
    fun save(coBuyingHistory: CoBuyingHistory): CoBuyingHistory
}