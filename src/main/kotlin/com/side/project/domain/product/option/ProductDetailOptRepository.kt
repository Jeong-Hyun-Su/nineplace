package com.side.project.domain.product.option

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun ProductDetailOptRepository.getByIds(id: UUID): ProductDetailOpt = findByIdOrNull(id)
    ?: throw NoSuchElementException("상세옵션이 존재하지 않습니다. id: $id")

@Repository
interface ProductDetailOptRepository: JpaRepository<ProductDetailOpt, UUID> {
}