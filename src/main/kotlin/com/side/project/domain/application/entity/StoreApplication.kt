package com.side.project.domain.application.entity

import com.side.project.global.common.code.status.StoreStatus
import com.side.project.global.common.code.status.StoreStatusConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "StoreApplication")
class StoreApplication (
    storeId: UUID,
    name: String? = null,
    introComment: String? = null,
    imageUrl: String? = null,
    address: String? = null,
    phoneNumber: String? = null,
    businessNumber: String? = null,
    openDate: LocalDate? = null,
    status: StoreStatus? = null,
    approval: Boolean = false
): BaseEntity() {
    @Column
    val storeId: UUID = storeId

    @Column(nullable = false)
    val name: String? = name

    @Column(nullable = false)
    val introComment: String? = introComment

    @Column(nullable = false)
    val imageUrl: String? = imageUrl

    @Column(nullable = false)
    val address: String? = address

    @Column(nullable = false)
    val phoneNumber: String? = phoneNumber

    @Column(nullable = false)
    val businessNumber: String? = businessNumber

    @Column(nullable = false)
    val openDate: LocalDate? = openDate

    @Convert(converter = StoreStatusConverter::class)
    @Column
    val status: StoreStatus? = status

    @Column(nullable = false)
    var approval: Boolean = approval
        protected set

    fun approve() {
        this.approval = true
    }
}