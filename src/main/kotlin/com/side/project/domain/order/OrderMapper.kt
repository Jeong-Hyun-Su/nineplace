package com.side.project.domain.order

import com.side.project.application.order.dto.OrderRequest
import com.side.project.application.order.dto.OrderDto
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper
interface OrderMapper {
    @Mappings
    fun toDto(order: Order): OrderDto

    @InheritInverseConfiguration
    fun ofOrder(orderRequest: OrderRequest): Order

    companion object {
        val INSTANCE = Mappers.getMapper(OrderMapper::class.java)
    }
}