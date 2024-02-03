package com.side.project.domain.product.repository

import com.side.project.domain.product.entity.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
): ProductRepository {
    override fun getByIds(id: UUID): Product {
        return productJpaRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("제품이 존재하지 않습니다. id: $id")
    }

    override fun existsById(uuid: UUID): Boolean {
        return productJpaRepository.existsById(uuid)
    }

    override fun save(product: Product): Product {
        return productJpaRepository.save(product)
    }
}