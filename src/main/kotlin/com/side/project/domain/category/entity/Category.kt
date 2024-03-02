package com.side.project.domain.category.entity

import com.side.project.domain.product.entity.Product
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "Category")
class Category (
    name: String
): BaseEntity() {

    @Column(nullable = false)
    var name: String = name
        protected set

    @OneToMany
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected val product: MutableList<Product> = mutableListOf()
    val products: List<Product> get() = product.toList()

    @OneToMany(mappedBy = "category", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    val detailCategory: MutableList<DetailCategory> = mutableListOf()
    val detailCategories: List<DetailCategory> get() = detailCategory.toList()
}