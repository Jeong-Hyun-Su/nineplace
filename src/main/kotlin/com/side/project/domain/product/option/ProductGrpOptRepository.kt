package com.side.project.domain.product.option

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun ProductGrpOptRepository.getByIds(id: Long): ProductGrpOpt = findByIdOrNull(id)
    ?: throw NoSuchElementException("그룹옵션이 존재하지 않습니다. id: $id")

@Repository
interface ProductGrpOptRepository: JpaRepository<ProductGrpOpt, Long> {
}