package com.side.project.domain.store.repository

import com.side.project.domain.store.entity.Store
import java.util.UUID

interface StoreRepository {
    fun getByIds(id: UUID): Store
    fun save(store: Store): Store
    fun existsById(id: UUID): Boolean
}