package com.grupo29.mback.ecommerce.resource.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.grupo29.mback.ecommerce.exception.RepositoryException
import com.grupo29.mback.ecommerce.exception.TypeException
import com.grupo29.mback.ecommerce.gateway.CartRepositoryGateway
import com.grupo29.mback.ecommerce.model.Cart
import com.grupo29.mback.ecommerce.model.Item
import com.grupo29.mback.ecommerce.resource.entity.CartEntity
import com.grupo29.mback.ecommerce.resource.entity.ItemEntity
import com.grupo29.mback.ecommerce.resource.spring.CartRepositorySpring
import org.springframework.stereotype.Component

@Component
class CartRepositoryGatewayImpl(
    private val cartRepositorySpring: CartRepositorySpring,
    private val mapper: ObjectMapper
) : CartRepositoryGateway {
    override suspend fun addCart(userId: String, items: List<Item>): Cart{
        return cartRepositorySpring.save(CartEntity(userId, items, mapper)).toDomain(mapper)
    }

    override suspend fun findByUserId(userId: String): Cart? {
        return cartRepositorySpring.findByUserId(userId)?.toDomain(mapper)
            ?: throw RepositoryException("Cart not found", TypeException.CART_NOT_FOUND.name)
    }

    override suspend fun deleteById(id: String) {
        return cartRepositorySpring.deleteById(id)
    }

}