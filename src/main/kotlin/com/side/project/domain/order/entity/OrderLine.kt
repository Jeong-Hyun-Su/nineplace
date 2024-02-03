package com.side.project.domain.order.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
class OrderLine(
    price: Long,
    quantity: Long,
    order: Order,
    coBuyingLineId: UUID
): BaseEntity() {
    @Column
    var price: Long = price
        protected set

    @Column
    var quantity: Long = quantity
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var order: Order = order
        protected set

    @Column
    val coBuyingLineId: UUID = coBuyingLineId
}