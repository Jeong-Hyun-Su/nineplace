package com.side.project.domain.product

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

    @ManyToOne
    @JoinColumn(name = "storeId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var store: Store?,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    var grpOpt: MutableList<ProductGrpOpt>? = ArrayList(),

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var category: Category?,

    @ManyToOne
    @JoinColumn(name = "detailCategoryId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var detailCategory: DetailCategory?,

    @OneToMany(mappedBy = "product")
    var order: MutableList<Order>? = ArrayList(),

    id: Long = 0L
): BaseEntity(id)