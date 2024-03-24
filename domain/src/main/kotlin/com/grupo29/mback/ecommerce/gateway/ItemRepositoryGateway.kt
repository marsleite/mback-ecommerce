package com.grupo29.mback.ecommerce.gateway

import com.grupo29.mback.ecommerce.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepositoryGateway {

    suspend fun getItems(): Flow<Item>

    suspend fun getItem(skuId: String): Item

    suspend fun addItem(item: Item, userId: String?): Item

    suspend fun updateItem(item: Item, userId: String): Item

    suspend fun deleteItem(skuId: String)

    suspend fun getItemsByUserId(userId: String): Flow<Item>
}