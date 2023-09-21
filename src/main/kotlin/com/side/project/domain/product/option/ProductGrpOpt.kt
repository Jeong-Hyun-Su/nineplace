package com.side.project.domain.product.option

import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.*
import java.util.ArrayList

@Entity
class ProductGrpOpt (
    @Column(nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product?,

    @OneToMany(mappedBy="grpOpt", cascade = [ CascadeType.PERSIST, CascadeType.REMOVE ], orphanRemoval = true)
    var detailOpt: MutableList<ProductDetailOpt>? = ArrayList(),

    id: Long = 0L
): BaseEntity(id)