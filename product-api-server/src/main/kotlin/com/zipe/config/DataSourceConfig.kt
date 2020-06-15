package com.zipe.config

import com.zipe.annotation.DBRouting
import com.zipe.config.base.BaseDataSourceConfig
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
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "multiEntityManager",
    basePackages = ["com.zipe"],
    transactionManagerRef = "multiTransactionManager"
)
class DataSourceConfig : BaseDataSourceConfig() {

    @Bean(name = [DBRouting.PRODUCT_DATASOURCE])
    @ConfigurationProperties(prefix = "spring.datasource.product")
    @Primary
    fun productDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = [DBRouting.PRIMARY_DATASOURCE])
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    fun primaryDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = [DBRouting.SECONDARY_DATASOURCE])
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun secondaryDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = [DBRouting.THIRD_DATASOURCE])
    @ConfigurationProperties(prefix = "spring.datasource.third")
    fun thirdDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = [DBRouting.FOURTH_DATASOURCE])
    @ConfigurationProperties(prefix = "spring.datasource.fourth")
    fun fourthDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun dataSource(): DynamicDataSource {
        return DynamicDataSource().run {

            val targetDataSources = mapOf<Any, Any>(
                "productDataSource" to productDataSource()
            )
            dataSourceNames.add(DBRouting.PRODUCT_DATASOURCE)
            val dataSource = DynamicDataSource().apply {
                this.setTargetDataSources(targetDataSources)
                this.setDefaultTargetDataSource(primaryDataSource())
                this.afterPropertiesSet()
            }
            dataSource
        }
    }

    @Primary
    @Bean(name = ["multiEntityManager"])
    fun multiEntityManager(): LocalContainerEntityManagerFactoryBean? {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(false)
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setShowSql(true)
        vendorAdapter.setPrepareConnection(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.zipe.entity")

        factory.setJpaPropertyMap(hibernateConfig())

        factory.dataSource = dataSource()
        return factory
    }

    @Bean(name = ["multiTransactionManager"])
    fun multiTransactionManager(): PlatformTransactionManager? {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = multiEntityManager()!!.getObject()
        return transactionManager
    }

}
