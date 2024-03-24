package com.grupo29.mback.ecommerce.application.handler

import com.grupo29.mback.ecommerce.usecase.CreateShoppingCartUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class CartHandler(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase
) {
    suspend fun shopping(request: ServerRequest): ServerResponse {
        val userId = request.pathVariable("userId")
        val result = createShoppingCartUseCase.execute(userId)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}