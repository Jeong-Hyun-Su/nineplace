package com.side.project.domain.application.service

import com.side.project.domain.application.mapper.StoreApplicationMapper
import com.side.project.domain.application.repository.ApplicationRepository
import com.side.project.domain.cobuying.entity.CoBuying
import com.side.project.domain.cobuying.repository.CoBuyingRepository
import com.side.project.domain.store.controller.dto.StoreRequest
import com.side.project.domain.store.repository.StoreRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class StoreApplicationService(
    private val applicationRepository: ApplicationRepository,
    private val storeRepository: StoreRepository,
    private val coBuyingRepository: CoBuyingRepository,
) {
    @Transactional
    fun create(storeId: UUID, storeRequest: StoreRequest) {
        applicationRepository.saveStoreApplication(
            storeRequest.apply { this.id = storeId }
                .let(StoreApplicationMapper.INSTANCE::of)
        )
    }

    @Transactional
    fun updateStore(id: UUID) {
        val application = applicationRepository.getStoreApplicationByIds(id)
        val store = storeRepository.getByIds(application.storeId)
        val coBuyings: List<CoBuying> = coBuyingRepository.findByStoreId(application.storeId)
        // 진행중인 주문건이 있으면, 스토어 수정 불가
        coBuyings.forEach{ it.isInProgress("진행중인 주문건이 있습니다. 수정할 수 없습니다.") }
        // 스토어 업데이트(신청내용)
        store.updateByApplication(application.let(StoreApplicationMapper.INSTANCE::toDto))
        // 스토어 신청건 승인
        application.approve()
    }
}