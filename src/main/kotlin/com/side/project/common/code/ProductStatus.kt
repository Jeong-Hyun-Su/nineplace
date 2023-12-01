package com.side.project.common.code

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class ProductStatus(@get:JsonValue val type: String){
        OPENED("상품등록"),
        REMOVE("상품종료")
}