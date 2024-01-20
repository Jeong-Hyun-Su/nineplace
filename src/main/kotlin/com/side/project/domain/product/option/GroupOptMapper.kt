package com.side.project.domain.product.option

import com.side.project.application.product.dto.GroupOptDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface GroupOptMapper {

    @InheritInverseConfiguration
    fun of(groupOptDto: GroupOptDto): ProductGroupOpt

    companion object {
        val INSTANCE = Mappers.getMapper(GroupOptMapper::class.java)
    }
}