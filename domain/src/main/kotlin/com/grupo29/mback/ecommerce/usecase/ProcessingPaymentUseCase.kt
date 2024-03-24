package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.PaymentGateway
import com.grupo29.mback.ecommerce.model.Checkout
import org.springframework.stereotype.Service

@Service
class ProcessingPaymentUseCase(
    private val mbackPaymentGateway: PaymentGateway
) {

    suspend fun execute(checkout: Checkout): Checkout {
        return mbackPaymentGateway.pay(checkout)
    }
}