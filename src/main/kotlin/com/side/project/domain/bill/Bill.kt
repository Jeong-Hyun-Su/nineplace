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
    var percent: Long,

    @OneToMany(mappedBy = "bill", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    var billProduct: MutableList<BillProduct> = ArrayList(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var order: Order

    // 구매자
    //@ManyToOne
    //@JoinColumn(name = "orderId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    //var user: User,
): BaseEntity()