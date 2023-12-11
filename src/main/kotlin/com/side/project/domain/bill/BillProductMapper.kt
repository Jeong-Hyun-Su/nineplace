package com.side.project.domain.bill

import com.side.project.application.bill.dto.BillProductCreateDto
import com.side.project.application.bill.dto.BillProductDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface BillProductMapper {
    @Mappings
    fun toDto(billProduct: BillProduct): BillProductDto

    @InheritInverseConfiguration
    fun ofBillProduct(billProductCreateDto: BillProductCreateDto): BillProduct

    companion object {
        val INSTANCE = Mappers.getMapper(BillProductMapper::class.java)
    }
}