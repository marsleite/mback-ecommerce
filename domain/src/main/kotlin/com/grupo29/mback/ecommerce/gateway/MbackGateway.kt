package com.grupo29.mback.ecommerce.gateway

import com.grupo29.mback.ecommerce.model.MbackUser

interface MbackGateway {

    suspend fun getMbackUser(userId: String): MbackUser

}