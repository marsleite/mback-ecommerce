package com.grupo29.mback.ecommerce.resource.gateway

import com.grupo29.mback.ecommerce.application.config.ApiConfig
import com.grupo29.mback.ecommerce.exception.ClientException
import com.grupo29.mback.ecommerce.exception.TypeException
import com.grupo29.mback.ecommerce.gateway.PaymentGateway
import com.grupo29.mback.ecommerce.model.Checkout
import com.grupo29.mback.ecommerce.model.PaymentStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

private const val EXCEPTION_MESSAGE = "cannot get information to payment"
private const val SERVER_MESSAGE_ERROR = "internal server error, try later again"

@Component
class PaymentGatewayHTTP(
  private val webClient: WebClient,
  private val apiConfig: ApiConfig
): PaymentGateway {
  override suspend fun pay(checkout: Checkout): Checkout {
    return webClient.post()
      .uri { path ->
        path
          .path(apiConfig.mbackPayment)
          .build()
      }
      .bodyValue(checkout.toPayment())
      .awaitExchange {
        when {
          it.statusCode().is2xxSuccessful -> it.awaitBody<Boolean>()
          it.statusCode().is4xxClientError -> throw handlerClientException()
          else -> throw handlerError()
        }
      }.takeIf { !it }
      ?.let {
        checkout.copy(status = PaymentStatus.REJECTED)
      } ?: checkout.copy(status = PaymentStatus.APPROVED)
  }

  private suspend fun handlerClientException() =
    EXCEPTION_MESSAGE.let { message ->
      ClientException(message, TypeException.NOT_AVAILABLE.name)
    }

  private suspend fun handlerError() =
    ClientException(SERVER_MESSAGE_ERROR, TypeException.NOT_AVAILABLE.name)
}