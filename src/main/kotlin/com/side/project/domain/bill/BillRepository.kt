package com.side.project.domain.bill

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun BillRepository.getByIds(id: Long): Bill = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문정보가 존재하지 않습니다. id: $id")

@Repository
interface BillRepository: JpaRepository<Bill, Long> {
}