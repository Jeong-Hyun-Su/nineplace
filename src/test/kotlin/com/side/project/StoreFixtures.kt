package com.side.project

import com.side.project.domain.store.Store
import java.time.LocalDateTime



fun createStore(
    name: String = "kotest 가게",
    address: String? = "매천로 881",
    certificated_yn: Boolean = false,
    business_number: String? = "423-520102",
    image_url: String? = "/assets/kotest/koest1.jpg",
    intro_comment: String? = "환영합니다. kotest 가게입니다.",
    phone_number: String? = "010-2020-3033",
): Store{
    return Store(name = name,
                 certificated_yn = certificated_yn,
                 address = address,
                 business_number = business_number,
                 image_url = image_url,
                 intro_comment = intro_comment,
                 phone_number = phone_number,
                 open_date = LocalDateTime.now())
}