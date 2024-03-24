package com.grupo29.mback.ecommerce.application.router

import com.grupo29.mback.ecommerce.application.dto.HttpErrorResponse
import com.grupo29.mback.ecommerce.application.router.extensions.TemplateHandler
import com.grupo29.mback.ecommerce.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ExceptionHandler(
  val exceptionHandler: Map<Set<String>, TemplateHandler>
) {

  suspend fun handler(e: Throwable, serverRequest: ServerRequest): ServerResponse {
    val (httpResponse, httpStatus) = when (e) {
      is BaseException -> {
        getExceptionHandler(e)?.handler(e) ?: internalServerError()
      }
      else -> {
        internalServerError()
      }
    }
    return ServerResponse.status(httpStatus).bodyValueAndAwait(httpResponse)
  }

  private suspend fun getExceptionHandler(e: BaseException) =
    filterExceptionByType(e.type).let { exceptionSelected ->
      exceptionSelected.values.firstOrNull()
    }

  private fun filterExceptionByType(type: String) =
    exceptionHandler.filter { (key, _) ->
      key.contains(type)
    }

  private fun internalServerError() = HttpErrorResponse(
    "INTERNAL_SERVER_ERROR",
    "Unknown error"
  ) to HttpStatus.INTERNAL_SERVER_ERROR
}