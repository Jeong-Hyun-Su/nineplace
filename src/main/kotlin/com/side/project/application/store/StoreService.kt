package com.side.project.application.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.application.store.dto.StoreRequest
import com.side.project.common.code.status.OrderStatus
import com.side.project.common.code.status.StoreStatus
import com.side.project.domain.order.Order
import com.side.project.domain.store.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val storeApplicationRepository: StoreApplicationRepository
){
    fun getById(id: UUID): StoreDto{
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toDto)
    }

    fun getNoProductById(id: UUID): StoreNoProductDto{
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toNotProductDto)
    }

    fun create(storeRequest: StoreRequest) {
        storeRepository.save(
            storeRequest.let(StoreMapper.INSTANCE::ofStore)
                        .apply { this.status = StoreStatus.OPEN }
        )
    }

    fun update(id: UUID, storeRequest: StoreRequest) {
        check(storeRepository.existsById(id)){ "존재하지 않는 스토어입니다. 수정할 수 없습니다." }
        val store = storeRepository.getByIds(id)

        // 진행중인 주문건이 있으면, 스토어 수정 불가
        store.order.forEach {
            check(it.status != OrderStatus.OPENED){ "진행중인 주문건이 있습니다. 수정할 수 없습니다." }
        }

        storeApplicationRepository.save(
            storeRequest.let(StoreApplicationMapper.INSTANCE::ofStoreApplication)
                        .apply {
                            this.store = store
                            this.approval = false
                        }
        )
    }
}