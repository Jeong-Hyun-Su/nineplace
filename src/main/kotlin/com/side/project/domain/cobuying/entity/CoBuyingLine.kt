package com.side.project.domain.cobuying.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
class CoBuyingLine (
    name: String,
    price: Long,
    quantityLimit: Long,
    quantityCount: Long = 0,
    coBuying: CoBuying,
    productId: UUID,
    grpOptId: UUID,
    detailOptId: UUID,
): BaseEntity() {
    @Column
    var name: String = name
        protected set

    @Column
    var price: Long = price
        protected set

    @Column
    var quantityLimit: Long = quantityLimit
        protected set

    @Column
    var quantityCount: Long = quantityCount
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coBuyingId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var coBuying: CoBuying = coBuying
        protected set

    @Column
    val productId: UUID = productId

    @Column
    val grpOptId: UUID = grpOptId

    @Column
    val detailOptId: UUID = detailOptId
}