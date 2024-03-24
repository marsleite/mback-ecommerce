package com.grupo29.mback.ecommerce.application.dto

import com.grupo29.mback.ecommerce.model.Item

data class ItemRequest(
  val skuId: String,
  val name: String,
  val description: String,
  val price: Double,
  val stock: Int
) {

  fun toItem() = Item(
    skuId = this.skuId,
    name = this.name,
    description = this.description,
    price = this.price,
    stock = this.stock
  )
}