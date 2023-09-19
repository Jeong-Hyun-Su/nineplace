package com.side.project.application.product

import com.side.project.application.product.dto.ProductGrpOptDto
import com.side.project.domain.product.Product
import com.side.project.domain.product.option.ProductDetailOptRepository
import com.side.project.domain.product.option.ProductGrpOptRepository
import com.side.project.domain.product.option.ProductOptionMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductOptionService(
    private val productGrpOptRepository: ProductGrpOptRepository,
    private val productDetailOptRepository: ProductDetailOptRepository,
){
    @Transactional
    fun saveGrpAndDetailOption(product: Product, grpOptDto: ProductGrpOptDto){
        val grpOpt = ProductOptionMapper.INSTANCE.ofGrpEntity(grpOptDto).apply {
                            this.product = product
                            productGrpOptRepository.save(this)
                     }

        grpOptDto.detailOpt?.forEach {
            ProductOptionMapper.INSTANCE.ofDetailEntity(it).apply {
                this.grpOpt = grpOpt
                productDetailOptRepository.save(this)
            }
        }
    }
}