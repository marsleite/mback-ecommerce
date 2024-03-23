package com.grupo29.mback.ecommerce.builder

import com.grupo29.mback.ecommerce.model.Item

class ItemBuilder {
    var skuId: String? = null
    var name: String = "Mouse wireless"
    var description: String = "Mouse bluetooth para notebook"
    var price: Double = 109.90
    var stock: Int = 10

    fun build() = Item(skuId, name, description, price, stock)

    companion object {
        fun build(block: (ItemBuilder.() -> Unit)? = null) = when (block) {
            null -> ItemBuilder().build()
            else -> ItemBuilder().apply(block).build()
        }
    }
}