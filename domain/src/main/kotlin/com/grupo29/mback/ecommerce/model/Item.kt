package com.grupo29.mback.ecommerce.model

data class Item(
    val skuId: String? = null,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int
)
