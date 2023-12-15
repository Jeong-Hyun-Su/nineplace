package com.side.project.common.code.status

import com.fasterxml.jackson.annotation.JsonValue

enum class DiscountStatus (@get:JsonValue val type: String) {
    ACTIVATE("활성화"), DEACTIVATE("비활성화"), REMOVE("삭제")
}