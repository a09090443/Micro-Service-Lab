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
    entityManagerFactoryRef = "primaryDbEntityManagerFactory", basePackages = [
        "com.zipe.repository"],
    transactionManagerRef = "primaryDbTransactionManager"
)
class DataSource1Config : BaseDataSourceConfig() {

    @Autowired
    private lateinit var baseHikariConfig: HikariConfig

//    private fun jpaVendorAdapter(): JpaVendorAdapter? {
//        val adapter = HibernateJpaVendorAdapter()
//        adapter.setDatabase(Database.MYSQL)
//        adapter.setShowSql(true)
//        adapter.setGenerateDdl(false)
//        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect")
//        return adapter
//    }

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Bean(name = ["primaryDbDataSource"])
    fun mySqlDataSource(): DataSource {
//        val dataSourceBuilder = DataSourceBuilder.create()
//        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
//        dataSourceBuilder.url(env.getProperty("spring.datasource.url"))
//        dataSourceBuilder.username(env.getProperty("spring.datasource.username"))
//        dataSourceBuilder.password(env.getProperty("spring.datasource.password"))
//        dataSourceBuilder.type(HikariDataSource::class.java)
//        return DataSourceBuilder.create().build()
        val ds = DataSourceBuilder.create().build()
        if (ds is HikariDataSource) {
            baseHikariConfig.copyStateTo(ds)
            return ds
        }
        return ds
    }

    @Primary
    @Bean(name = ["primaryDbEntityManagerFactory"])
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(false)
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setShowSql(true)
        vendorAdapter.setPrepareConnection(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.zipe.entity")


//        val jpaProperties: MutableMap<String, String?> = mutableMapOf()
//        jpaProperties["hibernate.dialect"] = env.getProperty("hibernate.dialect")
//        jpaProperties["hibernate.cache.region.factory_class"] = env.getProperty("hibernate.cache.region.factory_class")
//        jpaProperties["hibernate.cache.use_second_level_cache"] =
//            env.getProperty("hibernate.cache.use_second_level_cache")
//        jpaProperties["hibernate.cache.use_query_cache"] = env.getProperty("hibernate.cache.use_query_cache")
//        jpaProperties["hibernate.cache.use_minimal_puts"] = env.getProperty("hibernate.cache.use_minimal_puts")

        factory.setJpaPropertyMap(hibernateConfig())

        factory.dataSource = mySqlDataSource()
        return factory
    }

    @Primary
    @Bean(name = ["primaryDbTransactionManager"])
    fun transactionManager(
        @Qualifier("primaryDbEntityManagerFactory") primaryDbEntityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager? {
        return primaryDbEntityManagerFactory?.let { JpaTransactionManager(it) }
    }
}
