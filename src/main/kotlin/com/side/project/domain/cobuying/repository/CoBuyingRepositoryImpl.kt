package com.side.project.domain.cobuying.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.side.project.domain.cobuying.entity.CoBuying
import com.side.project.domain.cobuying.entity.QCoBuying.coBuying
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CoBuyingRepositoryImpl(
    private val coBuyingJpaRepository: CoBuyingJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
): CoBuyingRepository {
    override fun getByIds(id: UUID): CoBuying = coBuyingJpaRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException("주문이 존재하지 않습니다. id: $id")


    override fun findByStoreId(storeId: UUID): List<CoBuying> {
        return jpaQueryFactory.selectFrom(coBuying)
            .where(coBuying.storeId.eq(storeId))
            .fetch()
    }

    override fun findByProductId(productId: UUID): List<CoBuying> {
        return jpaQueryFactory.selectFrom(coBuying)
            .where(coBuying.productId.eq(productId))
            .fetch()
    }

    override fun save(coBuying: CoBuying): CoBuying = coBuyingJpaRepository.save(coBuying)
    override fun delete(coBuying: CoBuying) = coBuyingJpaRepository.delete(coBuying)

}