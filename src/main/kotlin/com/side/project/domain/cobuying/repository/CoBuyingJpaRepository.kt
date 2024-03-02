package com.side.project.domain.cobuying.repository

import com.side.project.domain.cobuying.entity.CoBuying
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoBuyingJpaRepository: JpaRepository<CoBuying, UUID> {
}