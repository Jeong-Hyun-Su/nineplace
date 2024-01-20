package com.side.project.domain.category

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.*

@Entity
@Table(name = "Category")
class Category (
    @Column(nullable = false)
    var name: String,

    @OneToMany
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: MutableList<Product>? = ArrayList(),

    @OneToMany(mappedBy = "category", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    var detailCategory: MutableList<DetailCategory>? = ArrayList()
): BaseEntity()