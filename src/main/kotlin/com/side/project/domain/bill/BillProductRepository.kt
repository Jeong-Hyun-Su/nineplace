package com.side.project.domain.bill

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

fun BillProductRepository.getByIds(id: UUID): BillProduct = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문상품이 존재하지 않습니다. id: $id")

@Repository
interface BillProductRepository: JpaRepository<BillProduct, UUID> {
}