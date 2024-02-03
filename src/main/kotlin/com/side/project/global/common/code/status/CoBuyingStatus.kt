package com.side.project.global.common.code.status

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class CoBuyingStatus(@get:JsonValue val type: String){
        OPENED("판매중"),
        CLOSED("판매중지"),
        REMOVE("삭제")
}