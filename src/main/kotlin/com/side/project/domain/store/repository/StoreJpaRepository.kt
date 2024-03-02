package com.side.project.domain.store.repository

import com.side.project.domain.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface StoreJpaRepository: JpaRepository<Store, UUID>{
}