package com.side.project.domain.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

fun DetailCategoryRepository.getByIds(id: UUID): DetailCategory = findByIdOrNull(id)
    ?: throw NoSuchElementException("상세 카테고리가 존재하지 않습니다. id: $id")

@Repository
interface DetailCategoryRepository: JpaRepository<DetailCategory, UUID> {
    fun existsByCategoryIdAndId(categoryId: UUID, id: UUID): Boolean
}