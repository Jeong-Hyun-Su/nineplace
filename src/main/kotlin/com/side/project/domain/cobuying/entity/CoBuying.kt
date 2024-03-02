package com.side.project.domain.cobuying.entity

import com.side.project.domain.cobuying.controller.dto.CoBuyingRequest
import com.side.project.domain.cobuying.controller.dto.CoBuyingUpdateRequest
import com.side.project.global.common.code.status.CoBuyingStatus
import com.side.project.global.common.code.status.CoBuyingStatusConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "CoBuying")
class CoBuying (
    title: String,
    content: String,
    price: Long,
    percentLimit: Long,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    userCapacity: Long,
    userCount: Long = 0,
    viewCount: Long = 0,
    productId: UUID,
    storeId: UUID,
): BaseEntity() {
    @Column(nullable = false)
    var title: String = title
        protected set

    @Column
    var content: String = content
        protected set

    @Column
    var price: Long = price
        protected set

    @Column
    var percentLimit: Long = percentLimit
        protected set

    @Column
    var startTime: LocalDateTime = startTime
        protected set

    @Column
    var endTime: LocalDateTime = endTime
        protected set

    @Column
    var userCapacity: Long = userCapacity
        protected set

    @Column
    var userCount: Long = userCount
        protected set

    @Column(nullable = false)
    var viewCount: Long = viewCount
        protected set

    @Column(nullable = false)
    @Convert(converter = CoBuyingStatusConverter::class)
    var status: CoBuyingStatus = CoBuyingStatus.CLOSED

    @Column
    val productId: UUID = productId

    @Column
    val storeId: UUID = storeId

    @OneToMany(mappedBy = "coBuying", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val coBuyingLine: MutableList<CoBuyingLine> = mutableListOf()
    val coBuyingLines: List<CoBuyingLine> get() = coBuyingLine.toList()

    fun increaseUserCount() {
        check( userCount + 1 <= userCapacity ){ "인원이 꽉 찼습니다." }
        this.userCount += 1
    }

    fun addLineItems(lineItems: List<CoBuyingLine>) {
        this.coBuyingLine.addAll(lineItems)
    }

    fun update(order: CoBuyingUpdateRequest) {
        this.title = order.title
        this.startTime = order.startTime
        this.endTime = order.endTime
        this.userCapacity = order.userCapacity
        this.status = order.status

        // 주문품목 최대수량 변경
        for(coBuyingLineDto in order.coBuyingLines){
            val coBuyingLine = this.coBuyingLine.firstOrNull { coBuyingLineDto.id == it.id }
            coBuyingLine?.fixQuantityLimit(coBuyingLineDto.quantityLimit)
        }
    }

    fun delete() {
        this.status = CoBuyingStatus.REMOVE
    }

    fun isInProgress(message: String) {
        check(this.status != CoBuyingStatus.OPENED){ message }
    }
    private fun isFinished() {
        check(status == CoBuyingStatus.OPENED){ "공동구매가 종료되었습니다." }
    }
    private fun isTimeWithin() {
        check(LocalDateTime.now().isBefore(endTime)){ "공동구매가 종료되었습니다." }
    }
    private fun isUserCapacityOvered() {
        check( userCount + 1 <= userCapacity ){
            status = CoBuyingStatus.CLOSED
            "인원이 꽉 찼습니다. 공동구매가 종료되었습니다."
        }
    }
    fun isOrderAvailable() {
        isFinished()
        isTimeWithin()
        isUserCapacityOvered()
    }

    companion object {
        fun create(coBuyingRequest: CoBuyingRequest, content: String, price: Long, storeId: UUID): CoBuying {
            return CoBuying(
                title = coBuyingRequest.title,
                content = content,
                price = price,
                percentLimit = coBuyingRequest.percentLimit,
                userCount = 0,
                userCapacity = coBuyingRequest.userCapacity,
                viewCount = 0,
                startTime = coBuyingRequest.startTime,
                endTime = coBuyingRequest.endTime,
                storeId = storeId,
                productId = coBuyingRequest.productId,
            )
        }
    }

    override fun toString(): String {
        return "CoBuying(title='$title', content='$content', price=$price, discountLimit=$percentLimit, startTime=$startTime, endTime=$endTime, clientLimit=$userCapacity, clientCount=$userCount, viewCount=$viewCount, status=$status)"
    }
}