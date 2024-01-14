package com.side.project.domain.client

import com.side.project.common.payload.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "Client")
class Client (
    @Column
    var name: String,
    @Column
    var nickname: String,
    @Column
    var phoneNumber: String,
    @Column
    var address: String
): BaseEntity()