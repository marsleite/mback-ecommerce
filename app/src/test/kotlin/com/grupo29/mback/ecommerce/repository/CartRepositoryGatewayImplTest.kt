package com.grupo29.mback.ecommerce.repository

import com.grupo29.mback.ecommerce.builder.ItemBuilder
import com.grupo29.mback.ecommerce.exception.RepositoryException
import com.grupo29.mback.ecommerce.gateway.CartRepositoryGateway
import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import com.grupo29.mback.ecommerce.integration.IntegrationTest
import com.grupo29.mback.ecommerce.resource.spring.CartRepositorySpring
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

@ExperimentalCoroutinesApi
class CartRepositoryGatewayImplTest(
    @Autowired private val cartRepositorySpring: CartRepositorySpring,
    @Autowired private val itemRepositoryGateway: ItemRepositoryGateway,
    @Autowired private val cartRepositoryGateway: CartRepositoryGateway
): IntegrationTest() {

    @Test
    fun `create cart with success`() = runTest {
        val userId = "1234345"
        val item1 = ItemBuilder.build()
        val item2 = ItemBuilder.build {
            name = "Teclado"
            price = 239.99
        }
        val createdItem1 = itemRepositoryGateway.addItem(item1, userId)
        val createdItem2 = itemRepositoryGateway.addItem(item2, userId)

        val items = listOf(createdItem1, createdItem2)
        val response = cartRepositoryGateway.addCart(userId, items)

        val findCart = cartRepositorySpring.findByUserId(response.userId)

        Assertions.assertEquals(findCart?.userId, response.userId)
        Assertions.assertEquals(349.89, findCart?.total)
    }

    @Test
    fun `delete cart by id successfully`() = runTest {
        val userId = "1234345"
        val item1 = ItemBuilder.build()
        val item2 = ItemBuilder.build {
            name = "Teclado"
            price = 239.99
        }
        val createdItem1 = itemRepositoryGateway.addItem(item1, userId)
        val createdItem2 = itemRepositoryGateway.addItem(item2, userId)

        val items = listOf(createdItem1, createdItem2)
        val createdCart = cartRepositoryGateway.addCart(userId, items)

        cartRepositoryGateway.deleteById(createdCart.id)

        val findCart = cartRepositorySpring.findByUserId(userId)

        Assertions.assertNull(findCart)

    }

    @Test
    fun `find cart by non-existing user id`() = runTest {
        val nonExistingUserId = "non-existing-user-id"

        assertThrows<RepositoryException> {
            cartRepositoryGateway.findByUserId(nonExistingUserId)
        }.let {
            Assertions.assertEquals("Cart not found", it.message)
        }

    }
}