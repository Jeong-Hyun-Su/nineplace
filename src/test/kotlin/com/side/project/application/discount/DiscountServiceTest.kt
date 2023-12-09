package com.side.project.application.discount

import com.side.project.common.code.discount.DiscountType
import com.side.project.createDiscount
import com.side.project.createOrder
import com.side.project.domain.discount.DiscountRepository
import com.side.project.domain.order.OrderRepository
import com.side.project.domain.order.getByIds
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class DiscountServiceTest : BehaviorSpec({
    val discountRepository = mockk<DiscountRepository>()
    val orderRepository = mockk<OrderRepository>()

    val discountService = DiscountService(discountRepository, orderRepository)

    Given("제품의 최대할인% 도달하면"){
        val createOrderLimitPercent = createOrder(discountLimit = 35L, price = 35000L,
                                                  discount = listOf(createDiscount(name = "섹션 1구간", type = DiscountType.SECTION, clientSection = 10L),
                                                                    createDiscount(name = "섹션 2구간", type = DiscountType.SECTION, clientSection = 20L),
                                                                    createDiscount(name = "프로모션", type = DiscountType.PROMOTION, percent = 20L),))

        every { orderRepository.getByIds(any()) } returns createOrderLimitPercent

        When("최대할인% 까지만 계산하고"){
            val discount = discountService.calculator(orderId = 1L)

            Then("결과를 반환한다"){
                discount.discountList[0].type shouldBe DiscountType.SECTION
                discount.percent shouldBe 35L
                discount.price shouldBe (35000 - (35000 * 0.35)).toLong()
            }
        }
    }

    Given("제품의 최대할인 도달하지 않으면"){
        val discount = listOf(createDiscount(name = "섹션 1구간", type = DiscountType.SECTION, clientSection = 10L, percent = 6L),
                              createDiscount(name = "섹션 2구간", type = DiscountType.SECTION, clientSection = 20L, percent = 8L),
                              createDiscount(name = "프로모션", type = DiscountType.PROMOTION, clientSection = 10L))

        val notLimitPercent =         createOrder(discountLimit = 35L, price = 35000L, clientCount = 9L, discount = discount)
        val notLimitPercentSection1 = createOrder(discountLimit = 35L, price = 35000L, clientCount = 15L, discount = discount)
        val notLimitPercentSection2 = createOrder(discountLimit = 35L, price = 35000L, clientCount = 20L, discount = discount)

        every { orderRepository.getByIds(1L) } returns notLimitPercent
        every { orderRepository.getByIds(2L) } returns notLimitPercentSection1
        every { orderRepository.getByIds(3L) } returns notLimitPercentSection2

        When("제품의 최대할인% 도달하지 않으면 적용된 %만 적용"){
            val discount = discountService.calculator(orderId = 1L)
            Then("섹션 X_프로모션 O"){
                discount.percent shouldBe 10L
                discount.price shouldBe (35000 - (35000 * 0.10)).toLong()
            }

            val discountSection1 = discountService.calculator(orderId = 2L)
            Then("섹션 1구간_프로모션 O"){
                discountSection1.percent shouldBe 16L
                discountSection1.price shouldBe (35000 - (35000 * 0.16)).toLong()
            }

            val discountSection2 = discountService.calculator(orderId = 3L)
            Then("섹션 2구간_프로모션 O"){
                discountSection1.percent shouldBe 18L
                discountSection1.price shouldBe (35000 - (35000 * 0.18)).toLong()
            }
        }
    }
})
