package com.side.project.domain.discount

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

fun DiscountRepository.getByIds(id: UUID): Discount = findByIdOrNull(id)
    ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")

@Repository
interface DiscountRepository: JpaRepository<Discount, UUID> {
    // 할인정보 ID 목록으로, 할인정보 리스트 조회
    @Query("select d from Discount d where d.id in :ids and d.status is true", nativeQuery = true)
    fun findDiscountsByIds(ids: List<UUID>): List<Discount>
}