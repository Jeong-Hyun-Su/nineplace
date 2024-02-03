package com.side.project.domain.category.controller

import com.side.project.domain.category.service.CategoryService
import com.side.project.domain.category.controller.dto.CategoryCreateDto
import com.side.project.domain.category.controller.dto.CategoryDto
import com.side.project.domain.category.controller.dto.DetailCategoryCreateDto
import com.side.project.domain.category.controller.dto.DetailCategoryDto
import com.side.project.global.common.payload.ApiResponse
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class CategoryController(
    private val categoryService: CategoryService,
) {
    @GetMapping("/category/{id}")
    fun categoryGet(@PathVariable id: UUID): ApiResponse<CategoryDto> {
        val category = categoryService.getCategoryById(id)

        return ApiResponse.ok(message = "카테고리 조회 성공", data = category)
    }

    @PostMapping("/category")
    fun categoryCreate(@RequestBody categoryCreateDto: CategoryCreateDto): ApiResponse<CategoryCreateDto> {
        categoryService.createCategory(categoryCreateDto)

        return ApiResponse.ok(message = "카테고리 생성 완료", data = categoryCreateDto)
    }

    @DeleteMapping("/category/{id}")
    fun categoryDelete(@PathVariable id: UUID): ApiResponse<Nothing> {
        categoryService.deleteCategory(id)

        return ApiResponse.ok(message = "카테고리 삭제 완료")
    }

    @GetMapping("/detail-category/{id}")
    fun detailCategoryGet(@PathVariable id: UUID): ApiResponse<DetailCategoryDto> {
        val detailCategory = categoryService.getDetailCategoryById(id)

        return ApiResponse.ok(message = "상세 카테고리 조회 성공", data = detailCategory)
    }

    @PostMapping("/detail-category")
    fun detailCategoryCreate(@RequestBody detailCategoryCreateDto: DetailCategoryCreateDto): ApiResponse<DetailCategoryCreateDto> {
        categoryService.createDetailCategory(detailCategoryCreateDto)

        return ApiResponse.ok(message = "상세 카테고리 생성 완료", data = detailCategoryCreateDto)
    }

    @DeleteMapping("/detail-category/{id}")
    fun detailCategoryDelete(@PathVariable id: UUID): ApiResponse<Nothing> {
        categoryService.deleteDetailCategory(id)

        return ApiResponse.ok(message = "상세 카테고리 삭제 완료")
    }
}