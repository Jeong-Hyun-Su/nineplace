package com.side.project.common.code

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ProductStatusConverter: AttributeConverter<ProductStatus, String> {
    override fun convertToDatabaseColumn(p: ProductStatus?): String? {
        return p?.type
    }

    override fun convertToEntityAttribute(p: String?): ProductStatus? {
        return ProductStatus::type findBy p
    }
}