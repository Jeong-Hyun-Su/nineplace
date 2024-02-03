package com.side.project.global.common.code.type

import com.side.project.global.common.code.findBy
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class DiscountTypeConverter: AttributeConverter<DiscountType, String> {
    override fun convertToDatabaseColumn(p0: DiscountType?): String? {
        return p0?.type
    }

    override fun convertToEntityAttribute(p0: String?): DiscountType? {
        return DiscountType::type findBy  p0
    }
}