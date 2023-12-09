package com.side.project.common.code.status

import com.side.project.common.code.findBy
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