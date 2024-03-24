package com.grupo29.mback.ecommerce.application.router

import com.grupo29.mback.ecommerce.application.handler.CartHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CartController(
  private val exceptionHandler: ExceptionHandler
) {

  @Bean
  fun createCart(cartHandler: CartHandler) = coRouter {
    POST("/mback/cart/{userId}", cartHandler::shopping)
    onError<Exception> { exception, request -> exceptionHandler.handler(exception, request)}
  }
}