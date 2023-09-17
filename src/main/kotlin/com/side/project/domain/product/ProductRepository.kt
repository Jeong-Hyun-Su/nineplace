package com.side.project.domain.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun ProductRepository.getByIds(id: Long): Product = findByIdOrNull(id)
        ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}