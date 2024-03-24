package com.grupo29.mback.ecommerce.resource.gateway

import com.grupo29.mback.ecommerce.application.config.ApiConfig
import com.grupo29.mback.ecommerce.application.dto.MbackUserRequest
import com.grupo29.mback.ecommerce.exception.ClientException
import com.grupo29.mback.ecommerce.exception.TypeException
import com.grupo29.mback.ecommerce.gateway.MbackUserGateway
import com.grupo29.mback.ecommerce.model.MbackUser
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

private const val EXCEPTION_MESSAGE = "cannot get information to %s"
private const val SERVER_MESSAGE_ERROR = "internal server error, try later again"

@Component
class MbackGatewayHTTP(
    private val webClient: WebClient,
    private val apiConfig: ApiConfig
): MbackUserGateway {
    override suspend fun getMbackUser(userId: String): MbackUser {
      return webClient.get()
          .uri { path ->
            path
              .path(apiConfig.mbackUser)
              .build(userId)
          }
          .awaitExchange {
            when {
              it.statusCode().is2xxSuccessful -> it.awaitBody<MbackUserRequest>()
              it.statusCode().is4xxClientError -> throw handlerClientException(userId)
              else -> throw handlerError()
            }
          }.toDomain()

    }

    private suspend fun handlerClientException(userId: String) =
      EXCEPTION_MESSAGE.format(userId).let { message ->
        ClientException(message, TypeException.NOT_AVAILABLE.name)
      }

    private suspend fun handlerError() =
      ClientException(SERVER_MESSAGE_ERROR, TypeException.NOT_AVAILABLE.name)

}
