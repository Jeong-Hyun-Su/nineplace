package com.side.project.global.common.code.status

import com.fasterxml.jackson.annotation.JsonValue

enum class StoreStatus (@get:JsonValue val type: String) {
    OPENED("영업중"), CLOSED("영업중지"), REMOVE("삭제")
}