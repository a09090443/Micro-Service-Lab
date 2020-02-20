package com.zipe

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.cloud.context.config.annotation.RefreshScope

@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
