package com.side.project.domain.history.event

import com.side.project.domain.cobuying.entity.CoBuying

data class ByCoBuyingCreateHistory (
    val coBuying: CoBuying
)