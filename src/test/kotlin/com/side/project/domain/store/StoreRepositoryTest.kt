package com.side.project.domain.store

import com.side.project.createStore
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class StoreRepositoryTest (
        private val storeRepository: StoreRepository
): ExpectSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Root))

    context("가게 조회") {
        val store = storeRepository.save(
            createStore()
        )

        expect("저장된 가게 조회"){
            val findStore = storeRepository.getByIds(store.id)
            findStore shouldBe store
        }
        expect("없는 가게 조회할 때"){
            val testId = 100L
            val exception = shouldThrow<NoSuchElementException> {
                storeRepository.getByIds(testId)
            }
            exception.message shouldBe "가게가 존재하지 않습니다. id: $testId"
        }
    }
})