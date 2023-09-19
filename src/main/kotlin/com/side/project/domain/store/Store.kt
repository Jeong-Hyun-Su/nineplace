package com.side.project.domain.store

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.Column
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
    var intro_comment: String?,

    @Column
    var image_url: String?,

    @Column
    var address: String?,

    @Column
    var phone_number: String?,

    @Column
    var business_number: String?,

    @Column
    var open_date: LocalDateTime?,

    @Column(nullable = false)
    var certificated_yn: Boolean,

    @OneToMany(mappedBy = "store")
    var product: MutableList<Product> = ArrayList(),

    id: Long = 0L
): BaseEntity(id)