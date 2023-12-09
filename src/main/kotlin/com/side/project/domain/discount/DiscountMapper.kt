package com.side.project.domain.discount

import com.side.project.application.discount.dto.DiscountDto
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface DiscountMapper {
    @Mappings
    fun toDto(discount: Discount): DiscountDto

    companion object {
        val INSTANCE = Mappers.getMapper(DiscountMapper::class.java)
    }
}