package com.grupo29.mback.ecommerce.application.config

import com.grupo29.mback.ecommerce.application.router.extensions.TemplateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansExceptionHandler {

  @Bean
  fun createHandlerException(handlerList: List<TemplateHandler>): Map<Set<String>, TemplateHandler> {
    return handlerList.associateBy { it.getTypes() }
  }
}