package com.side.project.common.code.status

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class ProductStatus(@get:JsonValue val type: String){
        OPENED("상품등록"),
        REMOVE("상품종료")
}