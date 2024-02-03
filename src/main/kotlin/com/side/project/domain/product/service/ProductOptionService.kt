package com.side.project.domain.product.service

import com.side.project.domain.product.controller.dto.ProductGroupOptDto
import com.side.project.domain.product.entity.Product
import com.side.project.domain.product.entity.ProductDetailOpt
import com.side.project.domain.product.entity.ProductGroupOpt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductOptionService(
){
    @Transactional
    fun create(options: List<ProductGroupOptDto>, product: Product) {
        for (groupOpt in options) {
            val groupOption = ProductGroupOpt(
                name = groupOpt.name,
                product = product
            )
            product.addGroupOption(groupOption)

            val detailOption = groupOpt.detail.map{
                ProductDetailOpt(
                    name = it.name,
                    price = it.price,
                    groupOpt = groupOption
                )
            }
            groupOption.addDetailOptionsList(detailOption)
        }
    }
}