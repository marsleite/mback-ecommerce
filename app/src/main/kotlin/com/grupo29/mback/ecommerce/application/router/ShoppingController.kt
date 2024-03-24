package com.grupo29.mback.ecommerce.application.router

import com.grupo29.mback.ecommerce.application.handler.ShoppingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ShoppingController(
  private val exceptionHandler: ExceptionHandler
) {

  @Bean
  fun createShopping(shoppingHandler: ShoppingHandler) = coRouter {
    POST("/mback/shopping", shoppingHandler::shopping)
    onError<Exception> { exception, request -> exceptionHandler.handler(exception, request)}
  }
}