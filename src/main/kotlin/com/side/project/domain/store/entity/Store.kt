package com.side.project.domain.store.entity

import com.side.project.domain.application.controller.dto.StoreApplicationDto
import com.side.project.domain.product.entity.Product
import com.side.project.global.common.code.status.StoreStatus
import com.side.project.global.common.code.status.StoreStatusConverter
import com.side.project.global.common.payload.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "Store")
class Store (
    name: String,
    introComment: String,
    imageUrl: String,
    address: String,
    phoneNumber: String,
    businessNumber: String,
    openDate: LocalDate,
    status: StoreStatus = StoreStatus.OPENED,
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var introComment: String = introComment
        protected set

    @Column(nullable = false)
    var imageUrl: String = imageUrl
        protected set

    @Column(nullable = false)
    var address: String = address
        protected set

    @Column(nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(nullable = false)
    var businessNumber: String = businessNumber
        protected set

    @Column(nullable = false)
    var openDate: LocalDate = openDate
        protected set

    @Convert(converter = StoreStatusConverter::class)
    @Column
    var status: StoreStatus = status
        protected set


    fun updateByApplication(application: StoreApplicationDto) {
        if(application.name != null)           this.name = application.name
        if(application.imageUrl != null)       this.imageUrl = application.imageUrl
        if(application.address != null)        this.address = application.address
        if(application.phoneNumber != null)    this.phoneNumber = application.phoneNumber
        if(application.businessNumber != null) this.businessNumber = application.businessNumber
        if(application.introComment != null)   this.introComment = application.introComment
        if(application.openDate != null)       this.openDate = application.openDate
        if(application.status != null)         this.status = application.status
    }
}