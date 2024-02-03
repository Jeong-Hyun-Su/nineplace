package com.side.project.domain.product.entity

import com.side.project.domain.product.controller.dto.ProductRequest
import com.side.project.domain.product.controller.dto.ProductUpdateRequest
import com.side.project.global.common.code.status.ProductStatus
import com.side.project.global.common.code.status.ProductStatusConverter
import com.side.project.global.common.payload.BaseEntity
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
    var categoryId: UUID = categoryId
        protected set

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

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val groupOpt: MutableList<ProductGroupOpt>  = mutableListOf()
    val groupOptions: List<ProductGroupOpt> get() = groupOpt.toList()

    @Column
    var coBuyingId: UUID? = null

    companion object {
        fun create(request: ProductRequest): Product {
            return Product(
                name = request.name,
                price = request.price,
                content = request.content,
                imageUrl = request.imageUrl,
                storeId = request.storeId,
                categoryId = request.categoryId,
            )
        }
    }
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

    fun changeCategory(categoryId: UUID) {
        this.categoryId = categoryId
    }

    fun existCoBuying(): Boolean {
        return this.coBuyingId != null
    }

    fun addGroupOption(groupOpt: ProductGroupOpt) {
        this.groupOpt.add(groupOpt)
    }
}