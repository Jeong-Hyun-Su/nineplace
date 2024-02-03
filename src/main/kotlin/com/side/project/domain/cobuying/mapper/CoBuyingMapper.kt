package com.side.project.domain.cobuying.mapper

import com.side.project.domain.cobuying.controller.dto.CoBuyingRequest
import com.side.project.domain.cobuying.controller.dto.CoBuyingDto
import com.side.project.domain.cobuying.entity.CoBuying
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper
interface CoBuyingMapper {
    @Mappings
    fun toDto(coBuying: CoBuying): CoBuyingDto

    @InheritInverseConfiguration
    @Mappings
    fun ofOrder(coBuyingRequest: CoBuyingRequest): CoBuying

    companion object {
        val INSTANCE = Mappers.getMapper(CoBuyingMapper::class.java)
    }
}