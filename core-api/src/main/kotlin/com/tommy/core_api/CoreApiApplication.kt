package com.tommy.core_api

import com.tommy.core_adapater.client.FeignConfiguration
import com.tommy.core_adapater.repository.jpa.JpaConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(scanBasePackages = ["com.tommy.*"])
@Import(JpaConfig::class, FeignConfiguration::class)
class CoreApiApplication

fun main(args: Array<String>) {
    runApplication<CoreApiApplication>(*args)
}
