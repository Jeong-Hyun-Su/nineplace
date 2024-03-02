package com.side.project.domain.store.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.side.project.domain.store.entity.Store
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StoreRepositoryImpl(
    private val storeJpaRepository: StoreJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): StoreRepository {
    override fun getByIds(id: UUID): Store = storeJpaRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException("가게가 존재하지 않습니다. id: $id")

    override fun save(store: Store): Store = storeJpaRepository.save(store)

    override fun existsById(id: UUID): Boolean = storeJpaRepository.existsById(id)
}