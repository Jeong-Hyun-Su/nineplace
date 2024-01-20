package com.side.project.domain.store

import com.side.project.common.code.status.StoreStatus
import com.side.project.common.code.status.StoreStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.order.Order
import com.side.project.domain.product.Product
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "Store")
class Store (
    @Column(nullable = false)
    var name: String,

    @Column
    var introComment: String?,

    @Column
    var imageUrl: String?,

    @Column
    var address: String?,

    @Column
    var phoneNumber: String?,

    @Column
    var businessNumber: String?,

    @Column
    var openDate: LocalDate?,

    @Column
    @Convert(converter = StoreStatusConverter::class)
    var status: StoreStatus,

    @OneToMany
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: MutableList<Product> = ArrayList(),

    @OneToMany(mappedBy = "store")
    var order: MutableList<Order> = ArrayList()
): BaseEntity()