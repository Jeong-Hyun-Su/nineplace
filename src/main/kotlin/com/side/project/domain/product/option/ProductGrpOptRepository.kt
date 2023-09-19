package com.side.project.domain.product.option

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductGrpOptRepository: JpaRepository<ProductGrpOpt, Long> {
}