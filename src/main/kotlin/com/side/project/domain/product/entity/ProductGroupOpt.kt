package com.side.project.domain.product.entity

import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class ProductGroupOpt (
    name: String,
    product: Product?,
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product? = product
        protected set

    @OneToMany(mappedBy="groupOpt", cascade = [ CascadeType.ALL ], orphanRemoval = true)
    protected var detailOpt: MutableList<ProductDetailOpt>? = mutableListOf()
    val detailOptions: List<ProductDetailOpt>? get() = detailOpt?.toList()

    companion object {
        fun create(name: String, product: Product): ProductGroupOpt {
            return ProductGroupOpt(
                name = name,
                product = product
            )
        }
    }

    fun addDetailOptionsList(detailOpts: List<ProductDetailOpt>){
        this.detailOpt = detailOpts.toMutableList()
    }
    fun addDetailOption(detailOpt: ProductDetailOpt) {
        this.detailOpt?.add(detailOpt)
    }
}