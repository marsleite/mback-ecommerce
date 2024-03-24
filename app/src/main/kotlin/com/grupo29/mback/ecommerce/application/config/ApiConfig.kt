package com.grupo29.mback.ecommerce.application.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api.internal")
data class ApiConfig(
    val uri: String,
    val mbackUser: String,
)