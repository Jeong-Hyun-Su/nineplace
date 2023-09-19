package com.side.project.domain.order

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.bill.Bill
import com.side.project.domain.product.Product
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Entity
@Table(name = "Orders")
class Order (
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column
    var startTime: LocalDateTime,

    @Column
    var endTime: LocalDateTime,

    @Column
    var clientMax: Long,

    @Column
    var clientCount: Long,

    @Column(nullable = false)
    var viewCount: Long,

    @Column(nullable = false)
    var status: Boolean,

    @ManyToOne
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product,

    @OneToMany(mappedBy = "order")
    var bill: MutableList<Bill> = ArrayList(),

    //등록자

    id: Long = 0L
): BaseEntity(id)