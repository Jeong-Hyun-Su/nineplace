package com.side.project.domain.store

import com.side.project.application.store.dto.StoreNoProductDto
import com.side.project.createStore
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StoreTest: StringSpec({
    "가게 Mapper" {
        val store: Store = createStore()
        val storeDto: StoreNoProductDto = StoreMapper.INSTANCE.toNotProductDto(store)

        assertSoftly (storeDto) {
            storeDto.name shouldBe "kotest 가게"
            storeDto.business_number shouldBe "423-520102"
        }
    }
})