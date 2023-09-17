package com.side.project.domain.product

import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.application.product.dto.ProductDto
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface ProductMapper {
    @Mappings
    fun toNoStoreDto(product: Product): ProductNoStoreDto

    @Mappings
    fun toDto(product: Product): ProductDto

    companion object{
        val INSTANCE = Mappers.getMapper(ProductMapper::class.java)
    }
}