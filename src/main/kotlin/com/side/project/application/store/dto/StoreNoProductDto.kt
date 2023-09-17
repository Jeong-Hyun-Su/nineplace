package com.side.project.application.store.dto

import java.time.LocalDateTime

data class StoreNoProductDto (
    var name: String?,
    var intro_comment: String?,
    var image_url: String?,
    var address: String?,
    var phone_number: String?,
    var business_number: String?,
    var open_date: LocalDateTime?,
    var certificated_yn: Boolean?,
)