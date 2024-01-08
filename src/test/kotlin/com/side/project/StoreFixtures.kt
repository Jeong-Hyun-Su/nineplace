package com.side.project

import com.side.project.common.code.status.StoreStatus
import com.side.project.domain.store.Store
import java.time.LocalDate
import java.time.LocalDateTime



fun createStore(
    name: String = "kotest 가게",
    address: String? = "매천로 881",
    businessNumber: String? = "423-520102",
    imageUrl: String? = "/assets/kotest/koest1.jpg",
    introComment: String? = "환영합니다. kotest 가게입니다.",
    phoneNumber: String? = "010-2020-3033",
    openDate: LocalDate = LocalDate.now(),
    status: StoreStatus = StoreStatus.OPEN,
): Store{
    return Store(name = name,
                 address = address,
                 businessNumber = businessNumber,
                 imageUrl = imageUrl,
                 introComment = introComment,
                 phoneNumber = phoneNumber,
                 openDate = openDate,
                 status = status)
}