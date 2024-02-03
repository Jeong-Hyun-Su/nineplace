package com.side.project.domain.history.mapper

import com.side.project.domain.cobuying.entity.CoBuying
import com.side.project.domain.history.entity.CoBuyingHistory
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface CoBuyingHistoryMapper {
    @Mappings(
        Mapping(source = "id", target = "coBuyingId")
    )
    fun of(coBuying: CoBuying): CoBuyingHistory
    companion object {
        val INSTANCE = Mappers.getMapper(CoBuyingHistoryMapper::class.java)
    }
}