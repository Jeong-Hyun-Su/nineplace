package com.side.project.domain.store

import com.side.project.application.store.dto.StoreRequest
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface StoreApplicationMapper {

    @InheritInverseConfiguration
    fun ofStoreApplication(storeRequest: StoreRequest): StoreApplication

    companion object {
        val INSTANCE = Mappers.getMapper(StoreApplicationMapper::class.java)
    }
}