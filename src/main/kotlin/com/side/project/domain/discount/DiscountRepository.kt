package com.side.project.domain.discount

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun DiscountRepository.getByIds(id: Long): Discount = findByIdOrNull(id)
    ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")

@Repository
interface DiscountRepository: JpaRepository<Discount, Long> {
    // 할인정보 ID 목록으로, 할인정보 리스트 조회
    @Query("select d from Discount d where d.id in :ids and d.status is true")
    fun findDiscountsByIds(ids: List<Long>): List<Discount>
}