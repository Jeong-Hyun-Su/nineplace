package com.side.project.common.code

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class OrderStatusConverter: AttributeConverter<OrderStatus, String> {
    override fun convertToDatabaseColumn(p: OrderStatus?): String? {
        return p?.type
    }

    override fun convertToEntityAttribute(p: String?): OrderStatus? {
        return OrderStatus::type findBy p
    }
}