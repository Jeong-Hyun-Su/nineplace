package com.side.project.domain.category

import com.side.project.application.category.dto.CategoryDto
import com.side.project.application.category.dto.DetailCategoryDto
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