package com.side.project.domain.store

import com.side.project.common.code.status.StoreStatus
import com.side.project.common.code.status.StoreStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.order.Order
import com.side.project.domain.product.Product
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

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
    var openDate: LocalDateTime?,

    @Column(nullable = false)
    var certificated: Boolean,

    @Convert(converter = StoreStatusConverter::class)
    @Column
    var status: StoreStatus,

    @OneToMany(mappedBy = "store")
    var product: MutableList<Product> = ArrayList(),

    @OneToMany(mappedBy = "store")
    var order: MutableList<Order> = ArrayList()
): BaseEntity()