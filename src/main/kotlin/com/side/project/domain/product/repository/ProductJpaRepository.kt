package com.side.project.domain.product.repository

import com.side.project.domain.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductJpaRepository: JpaRepository<Product, UUID> {
}