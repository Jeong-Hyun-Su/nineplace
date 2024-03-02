package com.side.project.domain.application.mapper

import com.side.project.domain.application.controller.dto.StoreApplicationDto
import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.application.entity.StoreApplication
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface StoreApplicationMapper {

    @Mappings
    fun of(storeRequest: StoreRequest): StoreApplication

    @Mappings
    fun toDto(storeApplication: StoreApplication): StoreApplicationDto

    companion object {
        val INSTANCE = Mappers.getMapper(StoreApplicationMapper::class.java)
    }
}