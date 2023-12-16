package com.side.project.common.code.status

import com.fasterxml.jackson.annotation.JsonValue

enum class StoreStatus (@get:JsonValue val type: String) {
    OPEN("영업중"), CLOSED("영업중지"), REMOVE("삭제")
}