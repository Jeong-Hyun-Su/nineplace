package com.side.project.domain.discount.entity

import com.side.project.domain.discount.controller.dto.DiscountSectionRequest
import com.side.project.global.common.code.status.DiscountStatus
import com.side.project.global.common.code.status.DiscountStatusConverter
import com.side.project.global.common.code.type.DiscountType
import com.side.project.global.common.code.type.DiscountTypeConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "Discount")
class Discount (
    name: String,
    type: DiscountType,
    percent: Long,
    userSection: Long = 0,
    amount: Long = 0,
    amountLimit: Long = 0,
    amountDuplicate: Long = 0,
    status: DiscountStatus,
    startDate: LocalDateTime,
    endDate: LocalDateTime,
    coBuyingId: UUID?
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Convert(converter = DiscountTypeConverter::class)
    @Column(nullable = false)
    val type: DiscountType = type

    @Column(nullable = false)
    var percent: Long = percent

    @Column(nullable = false)
    var userSection: Long? = userSection
        protected set

    @Column(nullable = false)
    var amount: Long = amount
        protected set

    @Column(nullable = false)
    val amountLimit: Long = amountLimit

    @Column(nullable = false)
    val amountDuplicate: Long = amountDuplicate

    @Column
    var startDate: LocalDateTime = startDate
        protected set

    @Column
    var endDate: LocalDateTime = endDate
        protected set

    @Convert(converter = DiscountStatusConverter::class)
    @Column(nullable = false)
    var status: DiscountStatus = status
        protected set

    @Column
    var coBuyingId: UUID? = coBuyingId
        protected set
    
    fun updateSection(discountSectionRequest: DiscountSectionRequest) {
        this.name = discountSectionRequest.name
        this.userSection = discountSectionRequest.userSection
        this.percent = discountSectionRequest.percent
    }

    fun delete() {
        this.status = DiscountStatus.REMOVE
    }

    fun use() {
        this.status = DiscountStatus.USE
    }
    fun activate() {
        this.status = DiscountStatus.ACTIVATE
    }
    fun deActivate() {
        this.status = DiscountStatus.DEACTIVATE
    }
    
    companion object {
        fun createSection(coBuyingId: UUID, discountSectionRequest: DiscountSectionRequest, startDate: LocalDateTime, endDate: LocalDateTime): Discount {
            return Discount(
                name = discountSectionRequest.name,
                userSection = discountSectionRequest.userSection,
                percent = discountSectionRequest.percent,
                startDate = startDate,
                endDate = endDate,
                status = DiscountStatus.DEACTIVATE,
                type = DiscountType.SECTION,
                coBuyingId = coBuyingId
            )
        }
    }
}