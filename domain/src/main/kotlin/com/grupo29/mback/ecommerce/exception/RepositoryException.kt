package com.grupo29.mback.ecommerce.exception

class RepositoryException(
    override val message: String,
    override val type: String,
    override val details: Map<String, String> = emptyMap(),
    val throwable: Throwable? = null
) : BaseException(message, type, details)