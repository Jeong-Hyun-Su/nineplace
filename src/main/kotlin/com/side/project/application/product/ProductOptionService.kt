package com.side.project.application.product

import com.side.project.application.product.dto.ProductGrpOptDto
import com.side.project.domain.product.Product
import com.side.project.domain.product.option.ProductGrpOptRepository
import com.side.project.domain.product.option.ProductOptionMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductOptionService(
    private val productGrpOptRepository: ProductGrpOptRepository
){

}