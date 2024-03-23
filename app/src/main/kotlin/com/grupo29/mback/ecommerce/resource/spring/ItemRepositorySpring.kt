package com.grupo29.mback.ecommerce.resource.spring

import com.grupo29.mback.ecommerce.resource.entity.ItemEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ItemRepositorySpring: CoroutineCrudRepository<ItemEntity, String> {

    suspend fun findByUserIdAndSkuId(userId: String, skuId: String): ItemEntity?
}