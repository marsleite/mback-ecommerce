package com.grupo29.mback.ecommerce.model

data class Cart(
    val id: String,
    val userId: String,
    val items: List<Item>,
    val total: Double
)
