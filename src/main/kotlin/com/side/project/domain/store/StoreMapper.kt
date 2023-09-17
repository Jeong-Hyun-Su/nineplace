package com.side.project.domain.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface StoreMapper {
    @Mappings
    fun toDto(store: Store): StoreDto

    @Mappings
    fun toNotProductDto(store: Store): StoreNoProductDto

    companion object {
        val INSTANCE = Mappers.getMapper(StoreMapper::class.java)
    }
}