package com.grupo29.mback.ecommerce.gateway

import com.grupo29.mback.ecommerce.model.Checkout

interface PaymentGateway {

  suspend fun pay(checkout: Checkout): Checkout
}