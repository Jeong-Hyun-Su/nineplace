package com.side.project.domain.cobuying.repository

import com.side.project.domain.cobuying.entity.CoBuying
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CoBuyingJpaRepository: JpaRepository<CoBuying, UUID> {
}