package com.side.project.domain.product.option

import com.side.project.application.product.dto.DetailOptDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface DetailOptMapper {

    @InheritInverseConfiguration
    fun of(detailOptDto: DetailOptDto): ProductDetailOpt

    companion object {
        val INSTANCE = Mappers.getMapper(DetailOptMapper::class.java)
    }
}