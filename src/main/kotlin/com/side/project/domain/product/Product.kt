package com.side.project.domain.product

import com.side.project.application.product.dto.ProductRequest
import com.side.project.application.product.dto.ProductUpdateRequest
import com.side.project.common.code.status.ProductStatus
import com.side.project.common.code.status.ProductStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.category.Category
import com.side.project.domain.order.Order
import com.side.project.domain.product.option.ProductGroupOpt
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "Product")
class Product(
    name: String,
    price: Long,
    imageUrl: String?,
    content: String,
    storeId: UUID,
    categoryId: UUID
): BaseEntity() {
    @Column
    val storeId: UUID = storeId

    @Column
    val categoryId: UUID = categoryId

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Long = price
        protected set

    @Column
    var imageUrl: String? = imageUrl
        protected set

    @Column
    var content: String = content
        protected set

    @Column
    @Convert(converter = ProductStatusConverter::class)
    var status: ProductStatus = ProductStatus.OPENED
        protected set


    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    protected val groupOpt: MutableList<ProductGroupOpt>  = mutableListOf()
    val groupOptions: List<ProductGroupOpt> get() = groupOpt.toList()

    @OneToMany(mappedBy = "product")
    protected val order: MutableList<Order> = mutableListOf()
    val orders: List<Order> get() = order.toList()

    fun update(productUpdateRequest: ProductUpdateRequest) {
        this.name = productUpdateRequest.name
        this.price = productUpdateRequest.price
        this.imageUrl = productUpdateRequest.imageUrl
        this.content = productUpdateRequest.content
        this.status = productUpdateRequest.status
    }

    fun delete() {
        this.status = ProductStatus.REMOVE
    }

    companion object {
        fun create(request: ProductRequest): Product {
            return Product(
                name = request.name,
                price = request.price,
                content = request.content,
                imageUrl = request.imageUrl,
                storeId = request.storeId,
                categoryId = request.categoryId
            )
        }
    }
}