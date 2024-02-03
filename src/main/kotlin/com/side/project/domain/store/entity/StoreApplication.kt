package com.side.project.domain.store.entity

import com.side.project.global.common.code.status.StoreStatus
import com.side.project.global.common.code.status.StoreStatusConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "StoreApplication")
class StoreApplication (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var store: Store?,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var introComment: String,

    @Column(nullable = false)
    var imageUrl: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var phoneNumber: String,

    @Column(nullable = false)
    var businessNumber: String,

    @Column(nullable = false)
    var openDate: LocalDate,

    @Convert(converter = StoreStatusConverter::class)
    @Column
    var status: StoreStatus?,

    @Column(nullable = false)
    var approval: Boolean
): BaseEntity()