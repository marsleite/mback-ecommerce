package com.grupo29.mback.ecommerce.gateway

import com.github.tomakehurst.wiremock.client.WireMock
import com.grupo29.mback.ecommerce.exception.ClientException
import com.grupo29.mback.ecommerce.integration.IntegrationTest
import com.grupo29.mback.ecommerce.resource.gateway.MbackGatewayHTTP
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@ExperimentalCoroutinesApi
class MbackUserGatewayHTTPTest: IntegrationTest() {

  @Autowired
  lateinit var target: MbackGatewayHTTP

  @Test
  fun `get user information with success`() {
    //given
    val userId = "1234"
    val jsonFilePath = "gateway/mback-user-response-success.json"

    //when
    WireMock.stubFor(
      WireMock.get(WireMock.urlPathEqualTo("/mback/user/$userId"))
        .willReturn(
          WireMock.aResponse().withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", "application/json")
            .withBody(readFileResponseJson(jsonFilePath))
        )
    )

    //then
    runTest {
      val result = target.getMbackUser(userId)
      Assertions.assertEquals("João Maria", result.name)
      Assertions.assertEquals("teste@teste.com", result.email)
      Assertions.assertEquals("Sao Paulo", result.address.city)
    }
  }

  @Test
  fun `get user information with client error`() {
    //given
    val userId = "1234"
    val jsonFilePath = "gateway/mback-user-response-success.json"

    //when
    WireMock.stubFor(
      WireMock.get(WireMock.urlPathEqualTo("/api/users/$userId"))
        .willReturn(
          WireMock.aResponse().withStatus(HttpStatus.BAD_REQUEST.value())
            .withHeader("Content-Type", "application/json")
            .withBody(readFileResponseJson(jsonFilePath))
        )
    )

    //then
    runTest {
      assertThrows<ClientException> {
        target.getMbackUser(userId)
      }.let {
        Assertions.assertEquals("cannot get information to 1234", it.message)
      }
    }
  }

  @Test
  fun `get user information with server error`() {
    //given
    val userId = "1234"
    val jsonFilePath = "gateway/mback-user-response-success.json"

    //when
    WireMock.stubFor(
      WireMock.get(WireMock.urlPathEqualTo("/api/users$userId"))
        .willReturn(
          WireMock.aResponse().withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .withHeader("Content-Type", "application/json")
            .withBody(readFileResponseJson(jsonFilePath))
        )
    )

    //then
    runTest {
      assertThrows<ClientException> {
        target.getMbackUser(userId)
      }.let {
        Assertions.assertEquals("internal server error, try later again", it.message)
      }
    }
  }
}