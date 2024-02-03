package com.side.project.domain.store.service

import com.side.project.domain.store.controller.dto.StoreDto
import com.side.project.domain.store.controller.dto.StoreNoProductDto
import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.store.mapper.StoreApplicationMapper
import com.side.project.domain.store.mapper.StoreMapper
import com.side.project.domain.store.repository.StoreApplicationRepository
import com.side.project.domain.store.repository.StoreRepository
import com.side.project.domain.store.repository.getByIds
import com.side.project.global.common.code.status.CoBuyingStatus
import com.side.project.global.common.code.status.StoreStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val storeApplicationRepository: StoreApplicationRepository
){
    fun getById(id: UUID): StoreDto {
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toDto)
    }

    fun getNoProductById(id: UUID): StoreNoProductDto {
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
        /*store.coBuying.forEach {
            check(it.status != CoBuyingStatus.OPENED){ "진행중인 주문건이 있습니다. 수정할 수 없습니다." }
        }*/

        storeApplicationRepository.save(
            storeRequest.let(StoreApplicationMapper.INSTANCE::ofStoreApplication)
                        .apply {
                            this.store = store
                            this.approval = false
                        }
        )
    }
}