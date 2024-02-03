package com.side.project.domain.order.entity

import com.side.project.domain.cobuying.controller.dto.CoBuyingLineDto
import com.side.project.domain.discount.controller.dto.CouponDiscountsDto
import com.side.project.domain.order.service.OrderCalculateService
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "Orders")
class Order (
    title: String,
    price: Long = 0,
    percent: Long = 0,
    coBuyingId: UUID
): BaseEntity() {
    @Column
    var title: String = title
        protected set

    @Column
    var price: Long = price
        protected set

    @Column
    var percent: Long = percent
        protected set

    @OneToMany(mappedBy = "order", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    protected val orderLine: MutableList<OrderLine> = mutableListOf()
    val orderLines: List<OrderLine> get() = orderLine.toList()

    @Column
    val coBuyingId: UUID = coBuyingId

    fun addLineItems(lineItems: List<CoBuyingLineDto>) {
        for(lineItem in lineItems) {
            this.price += lineItem.price * lineItem.quantity
            this.orderLine.add(
                OrderLine(
                    coBuyingLineId = lineItem.coBuyingLineId,
                    price = lineItem.price,
                    quantity = lineItem.quantity,
                    order = this
                )
            )
        }
    }

    fun calculatePercentAndPrice(orderCalculateService: OrderCalculateService, countDiscounts: List<CouponDiscountsDto>) {
        this.percent = orderCalculateService.getAllPercent(coBuyingId, countDiscounts)
        this.price = (this.price - (this.price * 0.01 * this.percent)).toLong()
    }

    // 구매자
    //@ManyToOne
    //@JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    //var user: User,
}