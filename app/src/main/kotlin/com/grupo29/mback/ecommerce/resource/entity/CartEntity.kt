package com.grupo29.mback.ecommerce.resource.entity

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.grupo29.mback.ecommerce.application.config.IdGenerator
import com.grupo29.mback.ecommerce.model.Cart
import com.grupo29.mback.ecommerce.model.Item
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table("cart")
data class CartEntity(
    @Id
    private var id: String? = null,
    val userId: String,
    val items: String,
    val total: Double
): Persistable<String> {
    constructor(userId: String, items: List<Item>, mapper: ObjectMapper) : this(
        userId = userId,
        items = mapper.writeValueAsString(items),
        total = items.sumOf<Item> { it.price }
    )

    fun toDomain(mapper: ObjectMapper) = Cart(
        id = id!!,
        userId = userId,
        items = mapper.readValue(items, Array<Item>::class.java).toList(),
        total = total
    )

    override fun getId(): String? {
        id = id ?: IdGenerator.tsid()
        return id
    }

    override fun isNew(): Boolean = id == null
}
