package com.side.project.domain.product

import com.side.project.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class ProductGrpOpt (
    @Column(nullable = false)
    var name: String,

    id: Long = 0L
): BaseEntity(id)