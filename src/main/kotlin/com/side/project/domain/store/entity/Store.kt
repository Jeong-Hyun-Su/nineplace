package com.side.project.domain.store.entity

import com.side.project.domain.product.entity.Product
import com.side.project.global.common.code.status.StoreStatus
import com.side.project.global.common.code.status.StoreStatusConverter
import com.side.project.global.common.payload.BaseEntity
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


): BaseEntity()