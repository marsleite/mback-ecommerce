package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import org.springframework.stereotype.Component

@Component
class FindItemsUseCase(
    private val itemRepository: ItemRepositoryGateway
) {
    suspend fun getAllItems() = itemRepository.getItems()

    suspend fun getItem(skuId: String) = itemRepository.getItem(skuId)
}