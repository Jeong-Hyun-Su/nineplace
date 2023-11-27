package com.side.project.application.category

import com.side.project.application.category.dto.CategoryCreateDto
import com.side.project.application.category.dto.CategoryDto
import com.side.project.application.category.dto.DetailCategoryCreateDto
import com.side.project.application.category.dto.DetailCategoryDto
import com.side.project.domain.category.*
import com.side.project.domain.order.OrderMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService (
    private val categoryRepository: CategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
){
    fun getCategoryById(id: Long): CategoryDto {
        return categoryRepository.getByIds(id)
                                 .let(CategoryMapper.INSTANCE::toCategoryDto)
    }

    fun getDetailCategoryById(id: Long): DetailCategoryDto {
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

    fun deleteCategory(id: Long) {
        check(categoryRepository.existsById(id)){ "삭제할 카테고리가 없습니다." }

        categoryRepository.deleteById(id)
    }

    fun deleteDetailCategory(id: Long) {
        check(detailCategoryRepository.existsById(id)){ "삭제할 상세 카테고리가 없습니다." }

        detailCategoryRepository.deleteById(id)
    }
}