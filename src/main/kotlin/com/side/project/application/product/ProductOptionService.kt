package com.side.project.application.product

import com.side.project.application.product.dto.GroupOptDto
import com.side.project.domain.product.Product
import com.side.project.domain.product.option.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductOptionService(
    private val productGroupOptRepository: ProductGroupOptRepository,
    private val productDetailOptRepository: ProductDetailOptRepository,
){
    fun create(options: List<GroupOptDto>, product: Product) {
        for(groupOpt in options) {
            val groupOption = productGroupOptRepository.save(
                ProductGroupOpt.of(groupOpt)
            )
            groupOption.setProduct(product)

            val detailOption = groupOpt.detailOpt.map(ProductDetailOpt::of)
            detailOption.forEach { it.setGroupOption(groupOption) }
            productDetailOptRepository.saveAll(detailOption)
        }
    }
}