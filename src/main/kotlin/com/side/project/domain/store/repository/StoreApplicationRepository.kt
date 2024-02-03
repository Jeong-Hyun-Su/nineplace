package com.side.project.domain.store.repository

import com.side.project.domain.store.entity.StoreApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun StoreApplicationRepository.getByIds(id: UUID): StoreApplication = findByIdOrNull(id)
    ?: throw NoSuchElementException("스토어 변경 신청 이력이 존재하지 않습니다. id: $id")

@Repository
interface StoreApplicationRepository: JpaRepository<StoreApplication, UUID> {
}