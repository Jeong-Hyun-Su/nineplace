package com.side.project.domain.cobuying.mapper

import com.side.project.domain.cobuying.controller.dto.CoBuyingLineRequest
import com.side.project.domain.cobuying.entity.CoBuyingLine
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface CoBuyingLineMapper {
    @Mappings
    fun of(coBuyingLineRequest: CoBuyingLineRequest): CoBuyingLine

    companion object {
        val INSTANCE = Mappers.getMapper(CoBuyingLineMapper::class.java)
    }
}