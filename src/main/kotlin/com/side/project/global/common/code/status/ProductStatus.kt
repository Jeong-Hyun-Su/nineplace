package com.side.project.global.common.code.status

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class ProductStatus(@get:JsonValue val type: String){
        OPENED("등록"),
        REMOVE("삭제")
}