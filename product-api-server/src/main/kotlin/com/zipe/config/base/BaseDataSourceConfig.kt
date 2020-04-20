package com.zipe.config.base

import com.zaxxer.hikari.HikariConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

open class BaseDataSourceConfig {

    @Autowired
    protected lateinit var env: Environment

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean("baseHikariConfig")
    open fun baseHikariConfig(): HikariConfig? {
        return HikariConfig()
    }

    open fun hibernateConfig(): MutableMap<String, String?> {
        val jpaProperties = mutableMapOf<String, String?>().apply {
            this["hibernate.dialect"] = env.getProperty("hibernate.dialect")
            this["hibernate.show_sql"] = env.getProperty("hibernate.show_sql")
            this["hibernate.format_sql"] = env.getProperty("hibernate.format_sql")
            this["hibernate.cache.region.factory_class"] = env.getProperty("hibernate.cache.region.factory_class")
            this["hibernate.cache.use_second_level_cache"] = env.getProperty("hibernate.cache.use_second_level_cache")
            this["hibernate.cache.use_query_cache"] = env.getProperty("hibernate.cache.use_query_cache")
            this["hibernate.cache.use_minimal_puts"] = env.getProperty("hibernate.cache.use_minimal_puts")
        }
        return jpaProperties
    }
}
