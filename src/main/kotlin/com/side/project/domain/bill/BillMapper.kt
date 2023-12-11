package com.side.project.domain.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface BillMapper {
    @Mappings
    fun toDto(bill: Bill): BillDto

    @InheritInverseConfiguration
    fun ofBill(billCreateDto: BillCreateDto): Bill

    companion object {
        val INSTANCE = Mappers.getMapper(BillMapper::class.java)
    }
}