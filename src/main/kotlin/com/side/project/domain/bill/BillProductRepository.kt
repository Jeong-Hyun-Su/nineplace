package com.side.project.domain.bill

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun BillProductRepository.getByIds(id: Long): BillProduct = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문상품이 존재하지 않습니다. id: $id")

@Repository
interface BillProductRepository: JpaRepository<BillProduct, Long> {
}