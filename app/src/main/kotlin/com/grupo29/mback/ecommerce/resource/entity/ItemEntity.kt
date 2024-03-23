package com.grupo29.mback.ecommerce.resource.entity

import com.grupo29.mback.ecommerce.application.config.IdGenerator
import com.grupo29.mback.ecommerce.model.Item
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table


@Table("products")
data class ItemEntity(
    @Id
    private var skuId: String? = null,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val userId: String? = null
): Persistable<String> {

    constructor(item: Item, userId: String?): this(
        skuId = item.skuId,
        name = item.name,
        description = item.description,
        price = item.price,
        stock = item.stock,
        userId = userId
    )

    fun toDomain() = Item(
        skuId = skuId,
        name = name,
        description = description,
        price = price,
        stock = stock
    )

    override fun getId(): String {
        skuId = skuId ?: IdGenerator.tsid()
        return skuId!!
    }
    override fun isNew(): Boolean = skuId == null
}
