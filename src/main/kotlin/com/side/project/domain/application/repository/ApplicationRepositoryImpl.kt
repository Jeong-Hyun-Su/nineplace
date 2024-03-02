package com.side.project.domain.application.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.side.project.domain.application.entity.StoreApplication
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ApplicationRepositoryImpl(
    private val storeApplicationJpaRepository: StoreApplicationJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): ApplicationRepository {
    override fun getStoreApplicationByIds(id: UUID): StoreApplication =
        storeApplicationJpaRepository.findByIdOrNull(id) ?: throw NoSuchElementException("가게 변경 신청이 존재하지 않습니다. id: $id")

    override fun saveStoreApplication(storeApplication: StoreApplication): StoreApplication =
        storeApplicationJpaRepository.save(storeApplication)

    override fun deleteStoreApplication(storeApplication: StoreApplication) =
        storeApplicationJpaRepository.delete(storeApplication)
}