package com.grupo29.mback.ecommerce.gateway

import com.grupo29.mback.ecommerce.model.Cart
import com.grupo29.mback.ecommerce.model.Item

interface CartRepositoryGateway {
    suspend fun addCart(userId: String, items: List<Item>): Cart
    suspend fun findByUserId(userId: String): Cart?
    suspend fun deleteById(id: String)
}