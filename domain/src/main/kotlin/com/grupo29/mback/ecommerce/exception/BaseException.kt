package com.grupo29.mback.ecommerce.exception

private const val ENABLE_SUPRESSION_TRUE = true
private const val WRITABLE_STACK_TRACE_FALSE = false
open class BaseException(
    override val message: String,
    open val type: String,
    open val details: Map<String, String>,
    private val throwable: Throwable? = null,
) : RuntimeException(message, throwable, ENABLE_SUPRESSION_TRUE, WRITABLE_STACK_TRACE_FALSE)