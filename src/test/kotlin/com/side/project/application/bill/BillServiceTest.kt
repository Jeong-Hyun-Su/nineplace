package com.side.project.application.bill

import com.side.project.*
import com.side.project.application.bill.dto.BillCreateDto
import com.side.project.application.bill.dto.BillProductCreateDto
import com.side.project.application.discount.DiscountService
import com.side.project.domain.bill.BillProductRepository
import com.side.project.domain.bill.BillRepository
import com.side.project.domain.discount.DiscountRepository
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import com.side.project.domain.product.option.ProductDetailOptRepository
import com.side.project.domain.product.option.ProductGrpOptRepository
import com.side.project.domain.product.option.getByIds
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BillServiceTest : BehaviorSpec({
    val billRepository = mockk<BillRepository>()
    val orderRepository = mockk<OrderRepository>()
    val billProductRepository = mockk<BillProductRepository>()
    val productGrpOptRepository = mockk<ProductGrpOptRepository>()
    val productDetailOptRepository = mockk<ProductDetailOptRepository>()
    val discountRepository = mockk< DiscountRepository>()

    val discountService = DiscountService(discountRepository)
    val billProductService = BillProductService(billProductRepository, productGrpOptRepository, productDetailOptRepository)
    val billService = BillService(billRepository, orderRepository, billProductService, discountService)

    Given("상품 구매 후,"){
        val order = createOrder(clientCount = 25, clientLimit = 30)

        every{ orderRepository.getByIds(any()) }   returns order
        every{ billRepository.save(any()) }        returns createBill(title = "bill", price = 0, order = order)
        every{ billProductRepository.save(any()) } returnsMany  listOf(createBillProduct(price = 10000 * 5), createBillProduct(price = 20000 * 3))
        every{ productGrpOptRepository.getByIds(any()) } returns createProductGrpOpt()
        every{ productDetailOptRepository.getByIds(1L) } returns createProductDetailOpt(price = 10000L)
        every{ productDetailOptRepository.getByIds(2L) } returns createProductDetailOpt(price = 20000L)

        When("Bill 생성"){
            billService.create(BillCreateDto(orderId = 1,
                                             title = "bill",
                                             billProduct = listOf(BillProductCreateDto(amount = 5, grpOptId = 1L, detailOptId = 1L),
                                                                  BillProductCreateDto(amount = 3, grpOptId = 1L, detailOptId = 2L)),
                                             discountList = null))

            Then("디버깅 시, price 정상 대입 확인"){}
        }
    }

    Given("상품 구매하였으나"){
        val order = createOrder(clientCount = 30, clientLimit = 30)
        every{ orderRepository.getByIds(any()) } returns order

        When("인원이 꽉 찼을 경우,"){
            val exception = shouldThrow<Exception> {
                billService.create(BillCreateDto(orderId = 1,
                                                 title = "bill",
                                                 billProduct = listOf(BillProductCreateDto(amount = 50000, grpOptId = 1L, detailOptId = 1L),
                                                                      BillProductCreateDto(amount = 70000, grpOptId = 1L, detailOptId = 2L)),
                                                 discountList = null))
            }
            Then("예외 메시지 호출"){
                exception.message shouldBe "인원이 꽉 찼습니다."
            }
        }
    }


    Given("상품 구매(동시성 체크)"){
        val order = createOrder(clientCount = 0, clientLimit = 500)

        every{ orderRepository.getByIds(any()) }   returns order
        every{ billRepository.save(any()) }        returns createBill(title = "bill", price = 0, order = order)
        every{ billProductRepository.save(any()) } returnsMany  listOf(createBillProduct(price = 10000 * 5), createBillProduct(price = 20000 * 3))
        every{ productGrpOptRepository.getByIds(any()) } returns createProductGrpOpt()
        every{ productDetailOptRepository.getByIds(1L) } returns createProductDetailOpt(price = 10000L)
        every{ productDetailOptRepository.getByIds(2L) } returns createProductDetailOpt(price = 20000L)

        When("Bill 생성"){
            val service: ExecutorService = Executors.newFixedThreadPool(4)

            repeat(500) {
                service.execute {
                    billService.create(
                        BillCreateDto(
                            orderId = 1,
                            title = "bill",
                            discountList = null,
                            billProduct = listOf(
                                BillProductCreateDto(amount = 5, grpOptId = 1L, detailOptId = 1L),
                                BillProductCreateDto(amount = 3, grpOptId = 1L, detailOptId = 2L)
                            )
                        )
                    )
                }
            }

            Then("order 정상적으로 완료하였는지").config(invocations = 30, threads = 30){
                order.clientCount shouldBe 500
            }
        }
    }
})
