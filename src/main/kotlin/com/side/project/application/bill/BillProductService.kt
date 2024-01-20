package com.side.project.application.bill

import com.side.project.application.bill.dto.BillProductRequest
import com.side.project.domain.bill.Bill
import com.side.project.domain.bill.BillProduct
import com.side.project.domain.bill.BillProductRepository
import com.side.project.domain.product.option.ProductDetailOptRepository
import com.side.project.domain.product.option.ProductGroupOptRepository
import com.side.project.domain.product.option.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BillProductService(
    private val billProductRepository: BillProductRepository,
    private val productGroupOptRepository: ProductGroupOptRepository,
    private val productDetailOptRepository: ProductDetailOptRepository
) {
    @Transactional
    fun create(billProductRequest: BillProductRequest, bill: Bill): BillProduct {
        val grpOpt = productGroupOptRepository.getByIds(billProductRequest.grpOptId)
        val detailOpt = productDetailOptRepository.getByIds(billProductRequest.detailOptId)

        return billProductRepository.save(
            BillProduct(
                bill = bill,
                grpOpt = grpOpt,
                detailOpt = detailOpt,
                amount = billProductRequest.amount,
                price = detailOpt.price * billProductRequest.amount
            )
        )
    }

    @Transactional
    fun createAll(billProductRequests: List<BillProductRequest>, bill: Bill): List<BillProduct> {
        val billProducts: ArrayList<BillProduct> = arrayListOf()

        for(billProductRequest in billProductRequests){
            billProducts.add(create(billProductRequest, bill))
        }
        return billProducts
    }

    fun getTotalPrice(billProducts: List<BillProduct>): Long {
        return billProducts.sumOf { it.price }
    }
}