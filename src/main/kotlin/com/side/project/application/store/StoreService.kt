package com.side.project.application.store

import com.side.project.application.store.dto.StoreDto
import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.domain.store.StoreMapper
import com.side.project.domain.store.StoreRepository
import com.side.project.domain.store.getByIds
import org.springframework.stereotype.Service

@Service
class StoreService(
    val storeRepository: StoreRepository
){
    fun getById(id: Long): StoreDto{
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toDto)
    }

    fun getNoProductById(id: Long): StoreNoProductDto{
        return storeRepository.getByIds(id)
                              .let(StoreMapper.INSTANCE::toNotProductDto)
    }
}