package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import com.grupo29.mback.ecommerce.model.Item
import org.springframework.stereotype.Component

@Component
class CreateItemUseCase(
  private val itemRepository: ItemRepositoryGateway
) {
  suspend fun execute(item: Item, userId: String) =
    itemRepository.addItem(item, userId)
}
