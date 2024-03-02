package com.side.project.domain.cobuying.service

import com.side.project.domain.cobuying.controller.dto.CoBuyingRequest
import com.side.project.domain.cobuying.controller.dto.CoBuyingDto
import com.side.project.domain.cobuying.controller.dto.CoBuyingUpdateRequest
import com.side.project.domain.cobuying.entity.CoBuying
import com.side.project.domain.cobuying.mapper.CoBuyingLineMapper
import com.side.project.domain.cobuying.mapper.CoBuyingMapper
import com.side.project.domain.cobuying.repository.CoBuyingRepository
import com.side.project.domain.discount.event.ByCoBuyingCreateEvent
import com.side.project.domain.discount.event.ByCoBuyingDeleteEvent
import com.side.project.domain.discount.event.ByCoBuyingUpdateEvent
import com.side.project.domain.history.event.ByCoBuyingCreateHistory
import com.side.project.domain.product.repository.ProductRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class CoBuyingService (
    private val publisher: ApplicationEventPublisher,
    private val coBuyingRepository: CoBuyingRepository,
    private val productRepository: ProductRepository
){
    fun getById(orderId: UUID): CoBuyingDto {
        return coBuyingRepository.getByIds(orderId)
                              .let(CoBuyingMapper.INSTANCE::toDto)
    }

    @Transactional
    fun create(coBuyingRequest: CoBuyingRequest) {
        // 상품 조회
        val product = productRepository.getByIds(coBuyingRequest.productId)

        // 공동구매 생성
        val coBuying = coBuyingRepository.save(
            CoBuying.create(
                coBuyingRequest,
                price = product.price,
                content = product.content,
                storeId = product.storeId
            )
        )
        // 공동구매 품목 생성
        coBuying.addLineItems(
            coBuyingRequest.coBuyingLines
                .map(CoBuyingLineMapper.INSTANCE::of)
        )
        // 섹션할인 생성
        publisher.publishEvent(
            ByCoBuyingCreateEvent(
                coBuyingId = coBuying.id,
                discount = coBuyingRequest.discounts,
                startTime = coBuyingRequest.startTime,
                endTime = coBuyingRequest.endTime
            )
        )
    }

    @Transactional
    fun update(id: UUID, coBuyingUpdateRequest: CoBuyingUpdateRequest) {
        val coBuying = coBuyingRepository.getByIds(id)
        // 공동구매 진행중이면, 수정 불가
        coBuying.isInProgress( "현재 공동구매가 진행중이므로, 수정할 수 없습니다." )
        // 공동구매, 품목(CoBuyingLine) 업데이트
        coBuying.update(coBuyingUpdateRequest)
        // 섹션할인 업데이트
        publisher.publishEvent(ByCoBuyingUpdateEvent(coBuyingId = id, discount = coBuyingUpdateRequest.discount))
    }

    @Transactional
    fun deleteById(id: UUID) {
        val coBuying = coBuyingRepository.getByIds(id)
        delete(coBuying)
    }

    @Transactional
    fun deleteByProductId(productId: UUID) {
        val coBuyings = coBuyingRepository.findByProductId(productId)
        coBuyings.forEach { delete(it) }
    }

    @Transactional
    fun delete(coBuying: CoBuying) {
        // 공동구매 진행중이면, 수정 불가
        coBuying.isInProgress( "현재 공동구매가 진행중이므로, 삭제할 수 없습니다." )
        // 공동구매, 품목(CoBuyingLine) 삭제
        coBuyingRepository.delete(coBuying)
        // 공동구매 히스토리 생성
        publisher.publishEvent(ByCoBuyingCreateHistory(coBuying))
        // 섹션할인 삭제
        publisher.publishEvent(ByCoBuyingDeleteEvent(coBuyingId = coBuying.id))
    }


}