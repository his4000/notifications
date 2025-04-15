package com.tommy.core_adapater.client

import feign.Request
import feign.RequestInterceptor
import feign.Retryer
import org.springframework.context.annotation.Bean
import java.time.Duration

class ServerApiClientConfiguration {
    @Bean
    fun requestInterceptor() = RequestInterceptor { template ->
        template.header("Content-Type", "application/json; charset=UTF-8")
    }

    @Bean
    fun retryer(): Retryer {
        val startRetryPeriodMillis = 100L
        val maxRetryPeriodMillis = 1000L
        val maxAttempts = 3
        return Retryer.Default(startRetryPeriodMillis, maxRetryPeriodMillis, maxAttempts)
    }

    @Bean
    fun feignOptions(): Request.Options {
        val connectTimeout = Duration.ofSeconds(3L)
        val readTimeout = Duration.ofSeconds(3L)
        return Request.Options(connectTimeout, readTimeout, true)
    }
}
