package com.side.project.common.code.status

import com.side.project.common.code.findBy
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StoreStatusConverter: AttributeConverter<StoreStatus, String> {
    override fun convertToDatabaseColumn(p: StoreStatus?): String? {
        return p?.type
    }

    override fun convertToEntityAttribute(p: String?): StoreStatus? {
        return StoreStatus::type findBy p
    }
}