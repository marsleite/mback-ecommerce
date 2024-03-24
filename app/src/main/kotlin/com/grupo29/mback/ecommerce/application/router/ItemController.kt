package com.grupo29.mback.ecommerce.application.router

import com.grupo29.mback.ecommerce.application.handler.ItemsHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

private const val PATH = "/mback-ecommerce"

@Configuration
class ItemController(
  private val exceptionHandler: ExceptionHandler
) {

  @Bean
  fun managementItem(itemsHandler: ItemsHandler) = coRouter {
    POST("$PATH/itens/registrar", itemsHandler::createItem)
    PUT("$PATH/itens/atualizar", itemsHandler::updateItem)
    DELETE("$PATH/itens/remover/{skuId}", itemsHandler::deleteItem)
    GET("$PATH/itens", itemsHandler::findItems)
    GET("$PATH/itens/{skuId}", itemsHandler::findItem)
    onError<Exception> { exception, request -> exceptionHandler.handler(exception, request)}
  }
}