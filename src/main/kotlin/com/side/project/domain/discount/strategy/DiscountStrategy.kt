package com.side.project.domain.discount.strategy

import com.side.project.application.discount.dto.DiscountDto

interface DiscountStrategy {
    fun calculator(): DiscountDto
}