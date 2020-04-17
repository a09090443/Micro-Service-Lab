package com.zipe.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean(name = ["primaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    fun primaryDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = ["secondaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun secondaryDataSource(): DataSource = DataSourceBuilder.create().build()

    @Autowired
    private lateinit var primaryDataSource: DataSource

    @Autowired
    private lateinit var secondaryDataSource: DataSource

    @Bean
    fun dataSource(): DynamicDataSource {
        return DynamicDataSource().run {
            val targetDataSources = mapOf<Any, Any>().apply {
                this["primaryDataSource" to primaryDataSource]
                this["secondaryDataSource" to secondaryDataSource]
            }
            dataSourceNames.add("primaryDataSource")
            dataSourceNames.add("secondaryDataSource")
            val dataSource = DynamicDataSource().apply {
                this.setTargetDataSources(targetDataSources)
                this.setDefaultTargetDataSource(primaryDataSource)
                this.afterPropertiesSet()
            }
            dataSource
        }
    }
}
