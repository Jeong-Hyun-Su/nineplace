package com.side.project.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

fun OrderRepository.getByIds(id: UUID): Order = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문이 존재하지 않습니다. id: $id")

@Repository
interface OrderRepository: JpaRepository<Order, UUID> {
}