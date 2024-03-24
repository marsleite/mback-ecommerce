package com.grupo29.mback.ecommerce.application.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

private const val CONTENT_TYPE = "Content-Type"
private const val MAX_CONNECTION = 1000
private const val DEFAULT = "default"

@Configuration
class WebClientConfig{

    @Bean
    fun webClientFactory(
        exchangeStrategies: ExchangeStrategies,
        apiConfig: ApiConfig
    ): WebClient {

        return webclientBuilder(apiConfig.uri, exchangeStrategies)
    }
    @Bean
    fun exchangeStrategies(mapper: ObjectMapper): ExchangeStrategies {
        return ExchangeStrategies
            .builder()
            .codecs { clientDefaultCodecsConfigurer ->
                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(
                    Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON)
                )
                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(
                    Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON)
                )
            }.build()
    }

    private fun buildHttpClient(name: String): ReactorClientHttpConnector {
        return ReactorClientHttpConnector(
            HttpClient
                .create(ConnectionProvider.create(name, MAX_CONNECTION))
                .compress(true)
        )
    }

    private fun webclientBuilder(baseUrl: String, exchangeStrategies: ExchangeStrategies)
            : WebClient {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(buildHttpClient(DEFAULT))
            .exchangeStrategies(exchangeStrategies)
            .build()
    }
}
