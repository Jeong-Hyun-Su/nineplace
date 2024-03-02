package com.side.project.domain.category.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class DetailCategory (
    name: String,
    category: Category
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var category: Category = category
        protected set
}