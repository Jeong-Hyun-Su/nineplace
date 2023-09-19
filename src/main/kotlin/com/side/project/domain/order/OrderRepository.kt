package com.side.project.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun OrderRepository.getByIds(id: Long): Order = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문이 존재하지 않습니다. id: $id")

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
}