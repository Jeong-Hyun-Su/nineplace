package com.side.project.domain.discount

import com.side.project.common.code.discount.DiscountType
import com.side.project.common.code.discount.DiscountTypeConverter
import com.side.project.common.code.status.OrderStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.order.Order
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Discount")
class Discount (
    @Column(nullable = false)
    var name: String,

    @Convert(converter = DiscountTypeConverter::class)
    @Column(nullable = false)
    var type: DiscountType,

    @Column(nullable = false)
    var percent: Long,

    @Column(nullable = false)
    var clientSection: Long,

    @Column(nullable = false)
    var amount: Long,

    @Column(nullable = false)
    var limitAmount: Long,

    @Column(nullable = false)
    var duplicateAmount: Long,

    @Column(nullable = false)
    var startDate: LocalDateTime?,

    @Column(nullable = false)
    var endDate: LocalDateTime?,

    @Column(nullable = false)
    var status: Boolean,

    @ManyToOne
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var order: Order?,
): BaseEntity()