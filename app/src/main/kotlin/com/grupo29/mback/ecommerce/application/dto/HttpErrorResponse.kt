package com.grupo29.mback.ecommerce.application.dto

data class HttpErrorResponse(
  val type: String,
  val message: String,
  val details: Map<String, String> = mapOf()
)
