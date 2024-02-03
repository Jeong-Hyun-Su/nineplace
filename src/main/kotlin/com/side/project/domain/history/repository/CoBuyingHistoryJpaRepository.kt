package com.side.project.domain.history.repository

import com.side.project.domain.history.entity.CoBuyingHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CoBuyingHistoryJpaRepository: JpaRepository<CoBuyingHistory, UUID> {
}