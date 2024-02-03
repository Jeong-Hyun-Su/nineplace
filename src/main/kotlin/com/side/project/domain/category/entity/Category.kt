package com.side.project.domain.category.entity

import com.side.project.domain.product.entity.Product
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "Category")
class Category (
    @Column(nullable = false)
    var name: String,

    @OneToMany
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: MutableList<Product> = mutableListOf(),

    @OneToMany(mappedBy = "category", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    var detailCategory: MutableList<DetailCategory> = mutableListOf(),
): BaseEntity()