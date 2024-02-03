package com.side.project.domain.history.entity

import com.side.project.global.common.code.status.CoBuyingStatus
import com.side.project.global.common.code.status.CoBuyingStatusConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "CoBuyingHistory")
class CoBuyingHistory (
    title: String,
    content: String,
    price: Long,
    percentLimit: Long,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    status: CoBuyingStatus,
    userCapacity: Long,
    userCount: Long,
    viewCount: Long,
    coBuyingId: UUID,
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
    var status: CoBuyingStatus = status

    @Column
    val productId: UUID = productId

    @Column
    val storeId: UUID = storeId

    @Column
    val coBuyingId: UUID = coBuyingId
}