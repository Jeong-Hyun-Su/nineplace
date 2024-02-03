package com.side.project.domain.category.service

import com.side.project.domain.category.controller.dto.CategoryCreateDto
import com.side.project.domain.category.controller.dto.CategoryDto
import com.side.project.domain.category.controller.dto.DetailCategoryCreateDto
import com.side.project.domain.category.controller.dto.DetailCategoryDto
import com.side.project.domain.category.mapper.CategoryMapper
import com.side.project.domain.category.repository.CategoryRepository
import com.side.project.domain.category.repository.DetailCategoryRepository
import com.side.project.domain.category.repository.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CategoryService (
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
){
    fun getCategoryById(id: UUID): CategoryDto {
        return categoryRepository.getByIds(id)
                                 .let(CategoryMapper.INSTANCE::toCategoryDto)
    }

    fun getDetailCategoryById(id: UUID): DetailCategoryDto {
        return detailCategoryRepository.getByIds(id)
                                       .let(CategoryMapper.INSTANCE::toDetailCategoryDto)
    }

    @Transactional
    fun createCategory(categoryCreateDto: CategoryCreateDto) {
        categoryRepository.save(
            categoryCreateDto.let(CategoryMapper.INSTANCE::ofCategory)
        )
    }

    @Transactional
    fun createDetailCategory(detailCategoryCreateDto: DetailCategoryCreateDto) {
        val category = categoryRepository.getByIds(detailCategoryCreateDto.categoryId)

        detailCategoryRepository.save(
            detailCategoryCreateDto.let(CategoryMapper.INSTANCE::ofDetailCategory)
                                   .apply { this.category = category }
        )
    }

    fun deleteCategory(id: UUID) {
        check(categoryRepository.existsById(id)){ "삭제할 카테고리가 없습니다." }

        categoryRepository.deleteById(id)
    }

    fun deleteDetailCategory(id: UUID) {
        check(detailCategoryRepository.existsById(id)){ "삭제할 상세 카테고리가 없습니다." }

        detailCategoryRepository.deleteById(id)
    }
}