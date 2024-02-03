package com.side.project.domain.discount.repository

import com.side.project.domain.discount.entity.Discount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DiscountJpaRepository: JpaRepository<Discount, UUID> {
    // 할인정보 ID 목록으로, 할인정보 리스트 조회
    @Query("select d from Discount d where d.id in :ids and d.status is true", nativeQuery = true)
    fun findDiscountsByIds(ids: List<UUID>): List<Discount>
}