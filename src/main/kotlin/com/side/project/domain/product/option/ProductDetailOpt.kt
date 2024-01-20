package com.side.project.domain.product.option

import com.side.project.application.product.dto.DetailOptDto
import com.side.project.common.payload.BaseEntity
import jakarta.persistence.*

@Entity
class ProductDetailOpt (
    name: String,
    price: Long,
    groupOpt: ProductGroupOpt,
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Long = price
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupOptId", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var groupOpt: ProductGroupOpt = groupOpt

    fun setGroupOption(groupOpt: ProductGroupOpt) {
        this.groupOpt = groupOpt
        groupOpt.addDetailOption(this)
    }

    companion object {
        fun of(detailOptDto: DetailOptDto): ProductDetailOpt {
            return detailOptDto.let(DetailOptMapper.INSTANCE::of)
        }
    }
}