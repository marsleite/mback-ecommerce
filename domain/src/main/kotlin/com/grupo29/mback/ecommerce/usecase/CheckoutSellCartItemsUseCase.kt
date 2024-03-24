package com.grupo29.mback.ecommerce.usecase

import com.grupo29.mback.ecommerce.gateway.CartRepositoryGateway
import com.grupo29.mback.ecommerce.gateway.MbackUserGateway
import com.grupo29.mback.ecommerce.model.Checkout
import org.springframework.stereotype.Component

@Component
class CheckoutSellCartItemsUseCase(
  private val cartRepositoryGateway: CartRepositoryGateway,
  private val processingPaymentUseCase: ProcessingPaymentUseCase,
  private val mbackUserGateway: MbackUserGateway
) {

  suspend fun execute(userId: String): Result<Checkout> {
    return runCatching {
      val user = mbackUserGateway.getMbackUser(userId)
      val checkout = cartRepositoryGateway.findByUserId(userId)
      ?.let {
        Checkout(
          userId = userId,
          email = user.email,
          address = user.address,
          total = it.total
        )
      } ?: throw RuntimeException("Cart not found")

      processingPaymentUseCase.execute(checkout)
    }.onFailure { it.cause }

  }
}
