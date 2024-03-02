package com.side.project.domain.store.service

import com.side.project.domain.application.event.ByStoreCreateEvent
import com.side.project.domain.store.controller.dto.StoreDto
import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.store.mapper.StoreMapper
import com.side.project.domain.store.repository.StoreRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class StoreService(
    private val publisher: ApplicationEventPublisher,
    private val storeRepository: StoreRepository,
){
    fun getById(id: UUID): StoreDto {
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(storeRequest: StoreRequest) {
        storeRepository.save(
            storeRequest.let(StoreMapper.INSTANCE::of)
        )
    }

    @Transactional
    fun update(id: UUID, storeRequest: StoreRequest) {
        check(storeRepository.existsById(id)) { "가게가 존재하지 않습니다." }
        // 스토어 변경 요청 생성
        publisher.publishEvent(ByStoreCreateEvent(storeId = id, storeRequest = storeRequest))
    }
}