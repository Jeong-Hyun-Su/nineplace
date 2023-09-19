package com.side.project.application.category

import com.side.project.application.category.dto.CategoryDto
import com.side.project.domain.category.*
import org.springframework.stereotype.Service

@Service
class CategoryService (
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
){
    fun getCategoryById(id: Long): CategoryDto{
        val category = categoryRepository.getByIds(id)

        return CategoryMapper.INSTANCE.toCategoryDto(category)
    }
}