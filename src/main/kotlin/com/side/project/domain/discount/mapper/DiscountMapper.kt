package com.side.project.domain.discount.mapper

import com.side.project.domain.discount.controller.dto.DiscountDto
import com.side.project.domain.discount.controller.dto.DiscountSectionRequest
import com.side.project.domain.discount.entity.Discount
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface DiscountMapper {
    @Mappings
    fun toDto(discount: Discount): DiscountDto

    @InheritInverseConfiguration
    fun ofDiscountInProduct(discountSectionRequest: DiscountSectionRequest): Discount


    companion object {
        val INSTANCE = Mappers.getMapper(DiscountMapper::class.java)
    }
}