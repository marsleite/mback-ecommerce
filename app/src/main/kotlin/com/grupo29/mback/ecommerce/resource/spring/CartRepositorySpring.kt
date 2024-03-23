package com.grupo29.mback.ecommerce.resource.spring

import com.grupo29.mback.ecommerce.resource.entity.CartEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CartRepositorySpring : CoroutineCrudRepository<CartEntity, String>{

    suspend fun findByUserId(userId: String): CartEntity?
}