package com.side.project.domain.category.repository

import com.side.project.domain.category.entity.DetailCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

fun DetailCategoryRepository.getByIds(id: UUID): DetailCategory = findByIdOrNull(id)
    ?: throw NoSuchElementException("상세 카테고리가 존재하지 않습니다. id: $id")

@Repository
interface DetailCategoryRepository: JpaRepository<DetailCategory, UUID> {
    fun existsByCategoryIdAndId(categoryId: UUID, id: UUID): Boolean
}