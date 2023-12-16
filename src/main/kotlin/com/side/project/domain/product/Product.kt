package com.side.project.domain.product

import com.side.project.application.product.dto.ProductDto
import com.side.project.application.product.dto.ProductUpdateRequest
import com.side.project.common.code.status.ProductStatus
import com.side.project.common.code.status.ProductStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.category.Category
import com.side.project.domain.category.DetailCategory
import com.side.project.domain.order.Order
import com.side.project.domain.product.option.ProductGrpOpt
import com.side.project.domain.store.Store
import jakarta.persistence.*
import java.util.ArrayList

@Entity
@Table(name = "Product")
class Product(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: Long,

    @Column
    var image_url: String?,

    @Column
    var content: String,

    @Column
    @Convert(converter = ProductStatusConverter::class)
    var status: ProductStatus,

    @ManyToOne
    @JoinColumn(name = "storeId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var store: Store,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    var grpOpt: MutableList<ProductGrpOpt>? = ArrayList(),

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var category: Category?,

    @ManyToOne
    @JoinColumn(name = "detailCategoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var detailCategory: DetailCategory?,

    @OneToMany(mappedBy = "product")
    val order: MutableList<Order> = ArrayList(),

): BaseEntity() {
    fun update(productUpdateRequest: ProductUpdateRequest) {
        this.name = productUpdateRequest.name
        this.price = productUpdateRequest.price
        this.image_url = productUpdateRequest.image_url
        this.content = productUpdateRequest.content
        this.status = productUpdateRequest.status
    }

    fun delete() {
        this.status = ProductStatus.REMOVE
    }
}