package com.side.project.domain.product.repository

import com.side.project.domain.product.entity.Product
import java.util.UUID

interface ProductRepository{
        fun getByIds(id: UUID): Product
        fun save(product: Product): Product
        fun existsById(uuid: UUID): Boolean
}