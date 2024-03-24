package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.CartRepositoryGateway
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Component

@Component
class CreateShoppingCartUseCase(
    private val cartRepositoryGateway: CartRepositoryGateway,
    private val findItemsUseCase: FindItemsUseCase
) {
    suspend fun execute(userId: String) {
      findItemsUseCase.getAllItemsByUserId(userId)
        .let { items -> cartRepositoryGateway.addCart(userId, items.toList()) }
    }

}