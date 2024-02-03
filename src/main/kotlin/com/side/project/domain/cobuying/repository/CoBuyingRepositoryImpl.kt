package com.side.project.domain.cobuying.repository

import com.side.project.domain.cobuying.entity.CoBuying
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CoBuyingRepositoryImpl(
    private val coBuyingJpaRepository: CoBuyingJpaRepository
): CoBuyingRepository {
    override fun getByIds(id: UUID): CoBuying = coBuyingJpaRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException("주문이 존재하지 않습니다. id: $id")

    override fun save(coBuying: CoBuying): CoBuying = coBuyingJpaRepository.save(coBuying)
    override fun delete(coBuying: CoBuying) = coBuyingJpaRepository.delete(coBuying)

}