package com.grupo29.mback.ecommerce.application.handler

import com.grupo29.mback.ecommerce.application.util.extractUserID
import com.grupo29.mback.ecommerce.usecase.CheckoutSellCartItemsUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ShoppingHandler(
  private val checkoutSellCartItemsUseCase: CheckoutSellCartItemsUseCase
) {

  suspend fun shopping(request: ServerRequest): ServerResponse {
    val userId = request.extractUserID()

    val response = checkoutSellCartItemsUseCase.execute(userId)

    return ServerResponse.ok().bodyValueAndAwait(response)

  }

}