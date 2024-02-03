package com.side.project.domain.order.mapper

import com.side.project.domain.order.controller.dto.OrderRequest
import com.side.project.domain.order.controller.dto.OrderDto
import com.side.project.domain.order.entity.Order
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface OrderMapper {
    @Mappings
    fun toOrderDto(order: Order): OrderDto

    @Mappings
    fun of(orderRequest: OrderRequest): Order

    companion object {
        val INSTANCE = Mappers.getMapper(OrderMapper::class.java)
    }
}