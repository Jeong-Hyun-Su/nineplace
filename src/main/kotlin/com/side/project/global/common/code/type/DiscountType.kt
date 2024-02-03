package com.side.project.global.common.code.type

import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
enum class DiscountType(@get:JsonValue val type: String){
    SECTION("섹션_할인"),
    PROMOTION("프로모션_할인"),
    COUPON("쿠폰_할인")
}