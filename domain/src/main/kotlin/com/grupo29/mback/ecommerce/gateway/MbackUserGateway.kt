package com.grupo29.mback.ecommerce.gateway

import com.grupo29.mback.ecommerce.model.MbackUser

interface MbackUserGateway {

    suspend fun getMbackUser(userId: String): MbackUser

}