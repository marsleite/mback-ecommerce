package com.grupo29.mback.ecommerce.application.router.extensions

import com.grupo29.mback.ecommerce.application.dto.HttpErrorResponse
import com.grupo29.mback.ecommerce.exception.BaseException
import com.grupo29.mback.ecommerce.exception.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class BadGatewayHandler: TemplateHandler() {
  override suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
    return HttpErrorResponse(e.type, e.message, e.details) to HttpStatus.BAD_GATEWAY
  }

  override suspend fun isTypeException(type: String): Boolean = getTypes().contains(type)

  override fun getTypes(): Set<String> = setOf(
    TypeException.NOT_AVAILABLE.name,
  )
}