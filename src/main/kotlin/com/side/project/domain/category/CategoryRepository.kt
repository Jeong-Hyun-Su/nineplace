package com.side.project.domain.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

fun CategoryRepository.getByIds(id: Long): Category = findByIdOrNull(id)
    ?: throw NoSuchElementException("카테고리가 존재하지 않습니다. id: $id")

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
}