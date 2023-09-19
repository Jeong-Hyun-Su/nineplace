package com.side.project.domain.product.option

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDetailOptRepository: JpaRepository<ProductDetailOpt, Long> {
}