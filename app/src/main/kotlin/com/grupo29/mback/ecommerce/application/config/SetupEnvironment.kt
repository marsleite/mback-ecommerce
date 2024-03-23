package com.grupo29.mback.ecommerce.application.config

import org.springframework.core.env.AbstractEnvironment
import java.util.*

fun setupEnv() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, getCurrentProfile())
}

fun getCurrentProfile(): String {
    val scope = System.getenv("SCOPE") ?: "development"
    return when {
        scope.contains("stage", true) -> "stage"
        scope.contains("production", true) -> "production"
        else -> scope
    }
}