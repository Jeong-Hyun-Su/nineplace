package com.side.project.application.category

import com.side.project.application.category.dto.CategoryDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val categoryService: CategoryService,
) {
    @GetMapping("/category/{id}")
    fun categoryGet(@PathVariable id: Long): CategoryDto {
        return categoryService.getCategoryById(id)
    }
}