package com.grupo29.mback.ecommerce.application.router.extensions

import com.grupo29.mback.ecommerce.application.dto.HttpErrorResponse
import com.grupo29.mback.ecommerce.exception.BaseException
import org.springframework.http.HttpStatus

abstract class TemplateHandler {

  abstract suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus>

  abstract suspend fun isTypeException(type: String): Boolean

  suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return if (isTypeException(e.type)) {
      applyHandler(e)
    } else {
      HttpErrorResponse("INTERNAL_SERVER_ERROR", "Unknown error") to HttpStatus.INTERNAL_SERVER_ERROR
    }
  }

  abstract fun getTypes(): Set<String>
}