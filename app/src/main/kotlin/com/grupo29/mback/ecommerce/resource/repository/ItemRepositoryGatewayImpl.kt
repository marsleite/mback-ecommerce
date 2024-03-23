package com.grupo29.mback.ecommerce.resource.repository

import com.grupo29.mback.ecommerce.exception.RepositoryException
import com.grupo29.mback.ecommerce.exception.TypeException
import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import com.grupo29.mback.ecommerce.model.Item
import com.grupo29.mback.ecommerce.resource.entity.ItemEntity
import com.grupo29.mback.ecommerce.resource.spring.ItemRepositorySpring
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component

@Component
class ItemRepositoryGatewayImpl(
    private val itemRepositorySpring: ItemRepositorySpring
): ItemRepositoryGateway {
    override suspend fun getItems(): Flow<Item> {
        return itemRepositorySpring.findAll().map { it.toDomain() }
    }

    override suspend fun getItem(skuId: String): Item {
        return itemRepositorySpring.findById(skuId)?.toDomain()
            ?: throw RepositoryException(
                "Item not found",
                TypeException.ITEM_NOT_FOUND.name
            )
    }

    override suspend fun addItem(item: Item, userId: String?): Item {
        return itemRepositorySpring.save(ItemEntity(item, userId)).toDomain()
    }

    override suspend fun updateItem(item: Item, userId: String): Item {
        return item.skuId?.let { sku ->
            itemRepositorySpring.findByUserIdAndSkuId(userId, sku)
                ?.let {
                    itemRepositorySpring.save(it.copy(
                        name = item.name,
                        description = item.description,
                        price = item.price,
                        stock = item.stock)
                    ).toDomain()
                } ?: throw RepositoryException("item to sku: [$sku] not found", TypeException.ITEM_NOT_FOUND.name)
        } ?: throw RepositoryException("sku cannot null", TypeException.ITEM_NOT_FOUND.name)
    }

    override suspend fun deleteItem(skuId: String) {
        itemRepositorySpring.deleteById(skuId)
    }
}