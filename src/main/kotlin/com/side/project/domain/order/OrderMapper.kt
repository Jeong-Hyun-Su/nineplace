package com.side.project.domain.order

import com.side.project.application.order.dto.OrderCreateDto
import com.side.project.application.order.dto.OrderDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface OrderMapper {
    @Mappings
    fun toDto(order: Order): OrderDto

    @InheritInverseConfiguration
    fun ofCreateEntity(orderCreateDto: OrderCreateDto): Order

    companion object {
        val INSTANCE = Mappers.getMapper(OrderMapper::class.java)
    }
}