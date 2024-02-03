package com.side.project.domain.store.mapper

import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.store.entity.StoreApplication
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface StoreApplicationMapper {

    @InheritInverseConfiguration
    fun ofStoreApplication(storeRequest: StoreRequest): StoreApplication

    companion object {
        val INSTANCE = Mappers.getMapper(StoreApplicationMapper::class.java)
    }
}