package com.side.project.global.common.payload

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.UUID

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : Persistable<UUID> {
    @Id
    private val id: UUID = UUID.randomUUID()


    @Transient
    private var _isNew = true

    @CreatedDate
    @JsonIgnore
    @Column(updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    @JsonIgnore
    @Column
    var updatedAt: LocalDateTime? = LocalDateTime.now()

    override fun getId(): UUID = id
    override fun isNew(): Boolean = _isNew

    @PostPersist
    @PostLoad
    protected fun load(){
        _isNew = false
    }
}