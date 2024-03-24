package com.grupo29.mback.ecommerce.application.handler

import com.grupo29.mback.ecommerce.application.dto.ItemRequest
import com.grupo29.mback.ecommerce.application.util.extractUserID
import com.grupo29.mback.ecommerce.usecase.CreateItemUseCase
import com.grupo29.mback.ecommerce.usecase.DeleteItemUseCase
import com.grupo29.mback.ecommerce.usecase.FindItemsUseCase
import com.grupo29.mback.ecommerce.usecase.UpdateItemUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ItemsHandler(
  private val createItemUseCase: CreateItemUseCase,
  private val updateItemUseCase: UpdateItemUseCase,
  private val deleteItemUseCase: DeleteItemUseCase,
  private val findItemsUseCase: FindItemsUseCase
) {

  suspend fun createItem(request: ServerRequest): ServerResponse {
    val userId = request.extractUserID()
    val item = request.awaitBody<ItemRequest>()
    val response = createItemUseCase.execute(item.toItem(), userId)

    return ServerResponse.ok().bodyValueAndAwait(response)
  }

  suspend fun updateItem(request: ServerRequest): ServerResponse {
    val userId = request.extractUserID()
    val item = request.awaitBody<ItemRequest>()
    val response = updateItemUseCase.execute(item.toItem(), userId)

    return ServerResponse.ok().bodyValueAndAwait(response)
  }

  suspend fun deleteItem(request: ServerRequest): ServerResponse {
    val skuId = request.pathVariable("skuId")
    val response = deleteItemUseCase.execute(skuId)

    return ServerResponse.ok().bodyValueAndAwait(response)
  }

  suspend fun findItems(request: ServerRequest): ServerResponse {
    val response = findItemsUseCase.getAllItems()

    return ServerResponse.ok().bodyValueAndAwait(response)
  }

  suspend fun findItem(request: ServerRequest): ServerResponse {
    val skuId = request.pathVariable("skuId")
    val response = findItemsUseCase.getItem(skuId)

    return ServerResponse.ok().bodyValueAndAwait(response)
  }

}
