package com.side.project.domain.product.option

import com.side.project.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class ProductDetailOpt (
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grpOptId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var grpOpt: ProductGrpOpt?,

    id: Long = 0L
): BaseEntity(id)