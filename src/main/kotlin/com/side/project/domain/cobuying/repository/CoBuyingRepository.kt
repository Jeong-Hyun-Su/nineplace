package com.side.project.domain.cobuying.repository

import com.side.project.domain.cobuying.entity.CoBuying
import java.util.UUID


interface CoBuyingRepository {
    fun getByIds(id: UUID): CoBuying
    fun save(coBuying: CoBuying): CoBuying
    fun delete(coBuying: CoBuying)
}