package com.side.project.domain.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun StoreRepository.getByIds(id: UUID): Store = findByIdOrNull(id)
    ?: throw NoSuchElementException("가게가 존재하지 않습니다. id: $id")

@Repository
interface StoreRepository: JpaRepository<Store, UUID>{
}