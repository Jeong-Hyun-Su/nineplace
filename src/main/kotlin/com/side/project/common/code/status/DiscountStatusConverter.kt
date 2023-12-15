package com.side.project.common.code.status

import com.side.project.common.code.findBy
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class DiscountStatusConverter: AttributeConverter<DiscountStatus, String> {
    override fun convertToDatabaseColumn(p: DiscountStatus?): String? {
        return p?.type
    }

    override fun convertToEntityAttribute(p: String?): DiscountStatus? {
        return DiscountStatus::type findBy p
    }
}