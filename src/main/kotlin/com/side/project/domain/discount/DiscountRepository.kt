package com.side.project.domain.discount

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun DiscountRepository.getByIds(id: Long): Discount = findByIdOrNull(id)
    ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")

@Repository
interface DiscountRepository: JpaRepository<Discount, Long> {
}