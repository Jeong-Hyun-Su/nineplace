package com.side.project.domain.order.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun OrderRepository.getByIds(id: UUID): com.side.project.domain.order.entity.Order = findByIdOrNull(id)
    ?: throw NoSuchElementException("주문정보가 존재하지 않습니다. id: $id")

@Repository
interface OrderRepository: JpaRepository<com.side.project.domain.order.entity.Order, UUID> {
}