package com.side.project.domain.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun ProductRepository.getByIds(id: UUID): Product = findByIdOrNull(id)
        ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")

@Repository
interface ProductRepository: JpaRepository<Product, UUID> {
}