package com.grupo29.mback.ecommerce.model

data class Checkout(
  val userId: String,
  val email: String,
  val address: Address,
  val total: Double,
  val status: PaymentStatus = PaymentStatus.PENDING
) {

  fun toPayment() = TotalPrice(
    userId = userId,
    totalPrice = total
  )
  data class TotalPrice(
    val userId: String,
    val totalPrice: Double)
}
