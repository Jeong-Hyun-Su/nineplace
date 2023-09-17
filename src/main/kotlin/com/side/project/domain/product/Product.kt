package com.side.project.domain.product

import com.fasterxml.jackson.annotation.JsonBackReference
import com.side.project.common.BaseEntity
import com.side.project.domain.store.Store
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Product(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: Long,

    @Column
    var image_url: String?,

    @ManyToOne
    @JoinColumn(name = "store_id")
    var store: Store,

    id: Long = 0L
): BaseEntity(id)