package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import org.springframework.stereotype.Component

@Component
class DeleteItemUseCase(
    private val itemRepository: ItemRepositoryGateway
) {
    suspend fun execute(skuId: String) =
        itemRepository.deleteItem(skuId)

}