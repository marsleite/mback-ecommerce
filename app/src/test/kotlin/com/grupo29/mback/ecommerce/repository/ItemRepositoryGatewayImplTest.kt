package com.grupo29.mback.ecommerce.repository

import com.grupo29.mback.ecommerce.builder.ItemBuilder
import com.grupo29.mback.ecommerce.gateway.ItemRepositoryGateway
import com.grupo29.mback.ecommerce.integration.IntegrationTest
import com.grupo29.mback.ecommerce.resource.spring.ItemRepositorySpring
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@ExperimentalCoroutinesApi
class ItemRepositoryGatewayImplTest: IntegrationTest() {

    @Test
    fun `create item with success`() = runTest {
        val item = ItemBuilder.build()
        val userId = "1234345"
//        val response = itemRepositoryGateway.addItem(item, userId)
//
//        val findItem = itemRepositorySpring.findById(response.skuId!!)
//
//        Assertions.assertEquals(findItem?.name, response.name)
    }
}