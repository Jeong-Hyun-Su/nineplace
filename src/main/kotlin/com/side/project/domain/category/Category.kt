package com.side.project.domain.category

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Category")
class Category (
    @Column(nullable = false)
    var name: String,

    @OneToMany(mappedBy = "category")
    var product: MutableList<Product?> = ArrayList(),

    @OneToMany(mappedBy = "category")
    var detailCategory: MutableList<DetailCategory?> = ArrayList(),

    id: Long = 0L
): BaseEntity(id)