package com.side.project.domain.product.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class ProductDetailOpt (
    name: String,
    price: Long,
    groupOpt: ProductGroupOpt,
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Long = price
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupOptId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var groupOpt: ProductGroupOpt = groupOpt

    companion object {
    }
}