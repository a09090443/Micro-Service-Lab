package com.zipe.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import com.zipe.config.base.BaseDataSourceConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "systemDbEntityManagerFactory",
    basePackages = ["com.zipe.repository.system"],
    transactionManagerRef = "systemDbTransactionManager"
)
class DataSourceSystemConfig : BaseDataSourceConfig() {

    @Autowired
    private lateinit var baseHikariConfig: HikariConfig

    @ConfigurationProperties(prefix = "spring.datasource.system")
    @Bean(name = ["systemDbDataSource"])
    fun mySqlDataSource(): DataSource {
        val ds = DataSourceBuilder.create().build()
        if (ds is HikariDataSource) {
            baseHikariConfig.copyStateTo(ds)
            return ds
        }
        return ds
    }

    @Bean(name = ["systemDbEntityManagerFactory"])
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(false)
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setShowSql(true)
        vendorAdapter.setPrepareConnection(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.zipe.entity.system")
        factory.setJpaPropertyMap(hibernateConfig())

        factory.dataSource = mySqlDataSource()
        return factory
    }

    @Bean(name = ["systemDbTransactionManager"])
    fun transactionManager(
        @Qualifier("systemDbEntityManagerFactory") systemDbEntityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager? {
        return systemDbEntityManagerFactory?.let { JpaTransactionManager(it) }
    }
}
