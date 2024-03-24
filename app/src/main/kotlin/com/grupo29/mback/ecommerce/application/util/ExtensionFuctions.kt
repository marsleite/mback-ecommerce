package com.grupo29.mback.ecommerce.application.util

import org.apache.coyote.BadRequestException
import org.springframework.web.reactive.function.server.ServerRequest
private const val MESSAGE_USER_ID_NULL = "user id cannot be null"
private const val USER_ID = "user.id"

fun ServerRequest.extractUserID() = this.queryParam(USER_ID)
  .orElseThrow { BadRequestException(MESSAGE_USER_ID_NULL) }