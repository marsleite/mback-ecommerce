package com.grupo29.mback.ecommerce.application.config

import com.github.f4b6a3.tsid.TsidCreator
import org.springframework.context.annotation.Configuration

@Configuration
class IdGenerator {

    companion object {
        @JvmStatic
        fun tsid(): String = TsidCreator.getTsid().toString()
    }
}