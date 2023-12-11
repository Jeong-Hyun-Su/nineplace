package com.side.project.application.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillDto
import com.side.project.common.payload.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bills")
class BillController(
    private val billService: BillService
) {
    @GetMapping("/{id}")
    fun billGet(@PathVariable id: Long): ApiResponse<BillDto> {
        val bill = billService.getById(id)

        return ApiResponse.ok(message = "Bill 조회 성공", data = bill)
    }

    @PostMapping
    fun billCreate(@RequestBody billCreateDto: BillCreateDto): ApiResponse<Nothing> {
        billService.create(billCreateDto)

        return ApiResponse.created(message = "Bill 생성 성공")
    }

    @DeleteMapping("/{id}")
    fun billDelete(@PathVariable id: Long): ApiResponse<Nothing> {
        billService.delete(id)

        return ApiResponse.ok(message = "Bill 삭제 성공")
    }
}