package com.side.project.domain.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.application.store.dto.StoreRequest
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface StoreMapper {
    @Mappings
    fun toDto(store: Store): StoreDto

    @Mappings
    fun toNotProductDto(store: Store): StoreNoProductDto

    @InheritInverseConfiguration
    fun ofStore(storeRequest: StoreRequest): Store

    companion object {
        val INSTANCE = Mappers.getMapper(StoreMapper::class.java)
    }
}