package com.side.project.domain.product.option

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

fun ProductGroupOptRepository.getByIds(id: UUID): ProductGroupOpt = findByIdOrNull(id)
    ?: throw NoSuchElementException("그룹옵션이 존재하지 않습니다. id: $id")

@Repository
interface ProductGroupOptRepository: JpaRepository<ProductGroupOpt, UUID> {
}