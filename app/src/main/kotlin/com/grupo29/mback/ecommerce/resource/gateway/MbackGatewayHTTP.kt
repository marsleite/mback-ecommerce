package com.grupo29.mback.ecommerce.resource.gateway

import com.grupo29.mback.ecommerce.gateway.MbackGateway
import com.grupo29.mback.ecommerce.model.MbackUser
import org.springframework.stereotype.Component

@Component
class MbackGatewayHTTP: MbackGateway {
    override suspend fun getMbackUser(userId: String): MbackUser {
        TODO("Not yet implemented")
    }
}