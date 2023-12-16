package com.side.project.domain.discount

import com.side.project.application.discount.dto.DiscountInProductUpdateRequest
import com.side.project.common.code.status.DiscountStatus
import com.side.project.common.code.status.DiscountStatusConverter
import com.side.project.common.code.type.DiscountType
import com.side.project.common.code.type.DiscountTypeConverter
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
    var type: DiscountType?,

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

    @Column
    var startDate: LocalDateTime?,

    @Column
    var endDate: LocalDateTime?,

    @Convert(converter = DiscountStatusConverter::class)
    @Column(nullable = false)
    var status: DiscountStatus?,

    @ManyToOne
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var order: Order?,
): BaseEntity() {

    fun update(discount: DiscountInProductUpdateRequest) {
        this.name = discount.name
        this.type = discount.type
        this.percent = discount.percent
        this.clientSection = discount.clientSection
        this.status = discount.status
    }

    fun delete() {
        this.status = DiscountStatus.REMOVE
    }
}