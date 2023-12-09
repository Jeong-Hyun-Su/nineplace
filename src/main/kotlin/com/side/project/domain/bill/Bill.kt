package com.side.project.domain.bill

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.order.Order
import jakarta.persistence.*

@Entity
class Bill (
    @Column
    var title: String,

    @Column
    var price: Long,

    @Column
    var grpOpt: String,

    @Column
    var detailOpt: String,

    // 구매자
    //@ManyToOne
    //@JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    //var user: User,

    @ManyToOne
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var order: Order,

    id: Long = 0L
): BaseEntity(id)