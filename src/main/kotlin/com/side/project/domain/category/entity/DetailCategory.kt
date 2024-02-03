package com.side.project.domain.category.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class DetailCategory (
    @Column(nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var category: Category,

    /*@OneToMany(mappedBy = "detailCategory")
    var product: MutableList<Product>? = ArrayList(),*/
): BaseEntity()