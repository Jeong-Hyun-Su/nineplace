package com.side.project.domain.product.option

import com.side.project.application.product.dto.ProductDetailOptDto
import com.side.project.application.product.dto.ProductGrpOptDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProductOptionMapper {
    @InheritInverseConfiguration
    fun ofGrpOpt(productGrpOptDto: ProductGrpOptDto): ProductGrpOpt

    @InheritInverseConfiguration
    fun ofDetailOpt(productDetailOptDto: ProductDetailOptDto): ProductDetailOpt

    companion object {
        val INSTANCE = Mappers.getMapper(ProductOptionMapper::class.java)
    }
}