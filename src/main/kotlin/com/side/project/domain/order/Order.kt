package com.side.project.domain.order

import com.side.project.application.order.dto.OrderUpdateRequest
import com.side.project.common.code.status.OrderStatus
import com.side.project.common.code.status.OrderStatusConverter
import com.side.project.common.payload.BaseEntity
import com.side.project.domain.bill.Bill
import com.side.project.domain.discount.Discount
import com.side.project.domain.product.Product
import com.side.project.domain.store.Store
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Orders")
class Order (
    @Column(nullable = false)
    var title: String,

    @Column
    var content: String,

    @Column
    var price: Long,

    @Column
    var discountLimit: Long,

    @Column
    var startTime: LocalDateTime,

    @Column
    var endTime: LocalDateTime,

    @Column
    var clientLimit: Long,

    @Column
    var clientCount: Long,

    @Column(nullable = false)
    var viewCount: Long,

    @Column(nullable = false)
    @Convert(converter = OrderStatusConverter::class)
    var status: OrderStatus?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var store: Store,

    //Section 할인율 정보
    @OneToMany(mappedBy = "order")
    var discount: MutableList<Discount> = ArrayList(),

    @OneToMany(mappedBy = "order")
    var bill: MutableList<Bill> = ArrayList(),

    //등록자
): BaseEntity() {
    fun increaseClientCount() {
        check( clientCount + 1 <= clientLimit ){ "인원이 꽉 찼습니다." }
        this.clientCount += 1
    }

    fun update(order: OrderUpdateRequest) {
        this.title = order.title
        this.startTime = order.startTime
        this.endTime = order.endTime
        this.clientLimit = order.clientLimit
        this.status = order.status
    }

    fun delete() {
        this.status = OrderStatus.REMOVE
    }

    override fun toString(): String {
        return "Order(title='$title', content='$content', price=$price, discountLimit=$discountLimit, startTime=$startTime, endTime=$endTime, clientLimit=$clientLimit, clientCount=$clientCount, viewCount=$viewCount, status=$status, product=$product, discount=$discount, bill=$bill)"
    }
}