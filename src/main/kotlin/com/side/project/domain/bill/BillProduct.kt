package com.side.project.domain.bill

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.option.ProductDetailOpt
import com.side.project.domain.product.option.ProductGrpOpt
import jakarta.persistence.*

@Entity
class BillProduct(
    @Column
    var price: Long,

    @Column
    var amount: Long,

    @ManyToOne
    @JoinColumn(name = "billId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var bill: Bill,

    @ManyToOne
    @JoinColumn(name = "grpOptId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var grpOpt: ProductGrpOpt,

    @ManyToOne
    @JoinColumn(name = "detailOptId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var detailOpt: ProductDetailOpt,
): BaseEntity()