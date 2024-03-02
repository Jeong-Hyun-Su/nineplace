package com.side.project.domain.category.mapper

import com.side.project.domain.category.controller.dto.CategoryCreateDto
import com.side.project.domain.category.controller.dto.CategoryDto
import com.side.project.domain.category.controller.dto.DetailCategoryCreateDto
import com.side.project.domain.category.controller.dto.DetailCategoryDto
import com.side.project.domain.category.entity.Category
import com.side.project.domain.category.entity.DetailCategory
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface CategoryMapper {
    @Mappings
    fun toCategoryDto(category: Category): CategoryDto

    @Mappings
    fun toDetailCategoryDto(detailCategory: DetailCategory): DetailCategoryDto

    companion object {
        val INSTANCE = Mappers.getMapper(CategoryMapper::class.java)
    }
}