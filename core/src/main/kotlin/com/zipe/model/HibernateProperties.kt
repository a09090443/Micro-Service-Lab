package com.zipe.model

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "hibernate")
data class HibernateProperties(
    var enable: Boolean = true,
    var key: Map<String, String> = mapOf()
)
