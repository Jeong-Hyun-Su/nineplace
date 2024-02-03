package com.side.project.global.common.code.status

import com.side.project.global.common.code.findBy
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class CoBuyingStatusConverter: AttributeConverter<CoBuyingStatus, String> {
    override fun convertToDatabaseColumn(p: CoBuyingStatus?): String? {
        return p?.type
    }

    override fun convertToEntityAttribute(p: String?): CoBuyingStatus? {
        return CoBuyingStatus::type findBy p
    }
}