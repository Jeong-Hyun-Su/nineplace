package com.side.project.domain.product.option

import com.side.project.application.product.dto.GroupOptDto
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.product.Product
import jakarta.persistence.*

@Entity
class ProductGroupOpt (
    name: String,
    product: Product,

): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product = product
        protected set

    @OneToMany(mappedBy="groupOpt", cascade = [ CascadeType.PERSIST, CascadeType.REMOVE ], orphanRemoval = true)
    protected var detailOpt: MutableList<ProductDetailOpt> = mutableListOf()
    val detailOptions: List<ProductDetailOpt> get() = detailOpt.toList()

    companion object {
        fun create(name: String, product: Product): ProductGroupOpt {
            return ProductGroupOpt(
                name = name,
                product = product
            )
        }

        fun of(groupOptDto: GroupOptDto): ProductGroupOpt {
            return groupOptDto.let(GroupOptMapper.INSTANCE::of)
        }
    }
    fun setProduct(product: Product) {
        this.product = product
    }

    fun addDetailOption(detailOpt: ProductDetailOpt) {
        this.detailOpt.add(detailOpt)
    }
}