package com.side.project.application.category

import com.side.project.application.category.dto.CategoryCreateDto
import com.side.project.application.category.dto.CategoryDto
import com.side.project.application.category.dto.DetailCategoryCreateDto
import com.side.project.application.category.dto.DetailCategoryDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
class CategoryController(
    private val categoryService: CategoryService,
) {
    @GetMapping("/category/{id}")
    fun categoryGet(@PathVariable id: Long): ApiResponse<CategoryDto> {
        val category = categoryService.getCategoryById(id)

        return ApiResponse.ok(message = "카테고리 조회 성공", data = category)
    }

    @PostMapping("/category")
    fun categoryCreate(@RequestBody categoryCreateDto: CategoryCreateDto): ApiResponse<CategoryCreateDto> {
        categoryService.createCategory(categoryCreateDto)

        return ApiResponse.ok(message = "카테고리 생성 완료", data = categoryCreateDto)
    }

    @DeleteMapping("/category/{id}")
    fun categoryDelete(@PathVariable id: Long): ApiResponse<Nothing> {
        categoryService.deleteCategory(id)

        return ApiResponse.ok(message = "카테고리 삭제 완료")
    }

    @GetMapping("/detail-category/{id}")
    fun detailCategoryGet(@PathVariable id: Long): ApiResponse<DetailCategoryDto> {
        val detailCategory = categoryService.getDetailCategoryById(id)

        return ApiResponse.ok(message = "상세 카테고리 조회 성공", data = detailCategory)
    }

    @PostMapping("/detail-category")
    fun detailCategoryCreate(@RequestBody detailCategoryCreateDto: DetailCategoryCreateDto): ApiResponse<DetailCategoryCreateDto> {
        categoryService.createDetailCategory(detailCategoryCreateDto)

        return ApiResponse.ok(message = "상세 카테고리 생성 완료", data = detailCategoryCreateDto)
    }

    @DeleteMapping("/detail-category/{id}")
    fun detailCategoryDelete(@PathVariable id: Long): ApiResponse<Nothing> {
        categoryService.deleteDetailCategory(id)

        return ApiResponse.ok(message = "상세 카테고리 삭제 완료")
    }
}