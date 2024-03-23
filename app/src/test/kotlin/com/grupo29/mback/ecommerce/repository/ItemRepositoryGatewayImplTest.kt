package com.grupo29.mback.ecommerce.repository

import com.grupo29.mback.ecommerce.builder.ItemBuilder
import com.grupo29.mback.ecommerce.exception.RepositoryException
import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import com.grupo29.mback.ecommerce.integration.IntegrationTest
import com.grupo29.mback.ecommerce.resource.spring.ItemRepositorySpring
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

@ExperimentalCoroutinesApi
class ItemRepositoryGatewayImplTest(
    @Autowired private val itemRepositoryGateway: ItemRepositoryGateway,
    @Autowired private val itemRepositorySpring: ItemRepositorySpring
): IntegrationTest() {

    @Test
    fun `create item with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        val findItem = itemRepositorySpring.findById(response.skuId!!)

        Assertions.assertEquals(findItem?.name, response.name)
    }

    @Test
    fun `update item with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        val itemUpdate = response.copy(
            name = "new name",
            description = "new description",
            price = 100.0,
            stock = 10
        )

        val responseUpdate = itemRepositoryGateway.updateItem(itemUpdate, userId)

        val findItem = itemRepositorySpring.findById(response.skuId!!)

        Assertions.assertEquals(findItem?.name, responseUpdate.name)
    }

    @Test
    fun `delete item with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        itemRepositoryGateway.deleteItem(response.skuId!!)

        val findItem = itemRepositorySpring.findById(response.skuId!!)

        Assertions.assertNull(findItem)
    }

    @Test
    fun `get item with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        val findItem = itemRepositoryGateway.getItem(response.skuId!!)

        Assertions.assertEquals(findItem.name, response.name)
    }

    @Test
    fun `get items with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        itemRepositoryGateway.addItem(item, userId)

        val items = itemRepositoryGateway.getItems()

        Assertions.assertEquals(1, items.count())
    }

    @Test
    fun `update item not found`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        val itemUpdate = response.copy(
            name = "new name",
            description = "new description",
            price = 100.0,
            stock = 10
        )

        assertThrows<RepositoryException> {
            itemRepositoryGateway.updateItem(itemUpdate, "123")
        }.let {
            Assertions.assertEquals("item to sku: [${response.skuId}] not found", it.message)
        }

    }

    @Test
    fun `should throw an exception when trying to update an item with skuId null`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
        val response = itemRepositoryGateway.addItem(item, userId)

        val itemUpdate = response.copy(
            name = "new name",
            description = "new description",
            price = 100.0,
            stock = 10,
            skuId = null
        )

        assertThrows<RepositoryException> {
            itemRepositoryGateway.updateItem(itemUpdate, userId)
        }.let {
            Assertions.assertEquals("sku cannot null", it.message)
        }
    }
}