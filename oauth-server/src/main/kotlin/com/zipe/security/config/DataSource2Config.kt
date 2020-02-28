package com.zipe.security.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import com.zipe.security.config.base.BaseDataSourceConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
    entityManagerFactoryRef = "secondaryDbEntityManagerFactory",
    basePackages = ["com.zipe.repository2"],
    transactionManagerRef = "secondaryDbTransactionManager"
)
class DataSource2Config : BaseDataSourceConfig() {

    @Autowired
    private lateinit var baseHikariConfig: HikariConfig

    @Bean(name = ["secondaryDbDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun mySqlDataSource(): DataSource {

        val ds = DataSourceBuilder.create().build()
        if (ds is HikariDataSource) {
            baseHikariConfig.copyStateTo(ds)
            return ds
        }
        return ds
    }

    @Bean(name = ["secondaryDbEntityManagerFactory"])
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(false)
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setShowSql(true)
        vendorAdapter.setPrepareConnection(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.zipe.entity2")

        factory.setJpaPropertyMap(hibernateConfig())

        factory.dataSource = mySqlDataSource()
        return factory
    }

    @Bean(name = ["secondaryDbTransactionManager"])
    fun transactionManager(
        @Qualifier("secondaryDbEntityManagerFactory") secondaryDbEntityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager? {
        return secondaryDbEntityManagerFactory?.let { JpaTransactionManager(it) }
    }
}
