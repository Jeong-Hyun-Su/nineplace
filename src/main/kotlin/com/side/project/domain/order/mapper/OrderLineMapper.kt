package com.side.project.domain.order.mapper

import com.side.project.domain.order.controller.dto.OrderLineRequest
import com.side.project.domain.order.controller.dto.OrderLineDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface OrderLineMapper {
    @Mappings
    fun toDto(orderLine: com.side.project.domain.order.entity.OrderLine): OrderLineDto

    @InheritInverseConfiguration
    fun of(orderLineRequest: OrderLineRequest): com.side.project.domain.order.entity.OrderLine

    companion object {
        val INSTANCE = Mappers.getMapper(com.side.project.domain.order.mapper.OrderLineMapper::class.java)
    }
}