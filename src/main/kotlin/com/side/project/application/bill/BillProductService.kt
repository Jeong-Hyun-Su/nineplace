package com.side.project.application.bill

import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillProductCreateDto
import com.side.project.domain.bill.Bill
import com.side.project.domain.bill.BillProduct
import com.side.project.domain.bill.BillProductRepository
import com.side.project.domain.product.option.ProductDetailOptRepository
import com.side.project.domain.product.option.ProductGrpOptRepository
import com.side.project.domain.product.option.getByIds
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BillProductService(
    private val billProductRepository: BillProductRepository,
    private val productGrpOptRepository: ProductGrpOptRepository,
    private val productDetailOptRepository: ProductDetailOptRepository
) {
    // 주문상품 생성
    fun create(billProductDto: BillProductCreateDto, bill: Bill): BillProduct {
        val grpOpt = productGrpOptRepository.getByIds(billProductDto.grpOptId)
        val detailOpt = productDetailOptRepository.getByIds(billProductDto.detailOptId)

        return billProductRepository.save(
            BillProduct(
                amount = billProductDto.amount,
                bill = bill,
                price = detailOpt.price * billProductDto.amount,
                grpOpt = grpOpt,
                detailOpt = detailOpt
            )
        )
    }

    // 주문상품 리스트 생성정및 최종가격 책
    fun createListAndReturnTotalPrice(billProductList: List<BillProductCreateDto>?, bill: Bill): Long{
        var price = 0L

        billProductList?.forEach {
            val billProduct = create(it, bill)
            price += billProduct.price
        }
        return price
    }
}