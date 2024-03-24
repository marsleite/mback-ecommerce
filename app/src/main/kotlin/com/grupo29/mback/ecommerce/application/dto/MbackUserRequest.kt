package com.grupo29.mback.ecommerce.application.dto

import com.grupo29.mback.ecommerce.model.Address
import com.grupo29.mback.ecommerce.model.MbackUser

data class MbackUserRequest(
  val nome: String,
  val email: String,
  val endereco: AddressRequest
) {

  data class AddressRequest(
    val rua: String,
    val cidade: String,
    val estado: String,
    val cep: String
  ) {

    fun toDomain() = Address(
      street = rua,
      city = cidade,
      state = estado,
      zipCode = cep
    )
  }

  fun toDomain() = MbackUser(
    name = nome,
    email = email,
    address = endereco.toDomain()
  )
}
