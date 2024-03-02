package com.side.project.domain.application.repository

import com.side.project.domain.application.entity.StoreApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StoreApplicationJpaRepository: JpaRepository<StoreApplication, UUID> {
}