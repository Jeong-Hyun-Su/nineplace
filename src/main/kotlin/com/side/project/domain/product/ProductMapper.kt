package com.side.project.domain.product

import com.side.project.application.product.dto.ProductCreateDto
import com.side.project.application.product.dto.ProductNoStoreDto
import com.side.project.application.product.dto.ProductDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface ProductMapper {
    @Mappings
    fun toNoStoreDto(product: Product): ProductNoStoreDto

    @Mappings
    fun toDto(product: Product): ProductDto

    @InheritInverseConfiguration
    fun ofProduct(productCreateDto: ProductCreateDto): Product

    companion object{
        val INSTANCE = Mappers.getMapper(ProductMapper::class.java)
    }
}