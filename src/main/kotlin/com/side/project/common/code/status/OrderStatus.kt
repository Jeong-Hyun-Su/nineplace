package com.side.project.common.code.status

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class OrderStatus(@get:JsonValue val type: String){
        OPENED("판매중"),
        CLOSED("판매중지"),
        REMOVE("삭제")
}