package com.side.project.domain.category

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.*

@Entity
class DetailCategory (
    @Column(nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var category: Category,

    @OneToMany(mappedBy = "detailCategory")
    var product: MutableList<Product?> = ArrayList(),

    id: Long = 0L,
): BaseEntity(id)