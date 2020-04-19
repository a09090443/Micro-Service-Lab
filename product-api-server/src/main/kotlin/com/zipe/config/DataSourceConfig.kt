package com.zipe.config

import com.zipe.config.base.BaseDataSourceConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "multiEntityManager",
    basePackages = ["com.zipe"],
    transactionManagerRef = "multiTransactionManager"
)
class DataSourceConfig : BaseDataSourceConfig() {

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

            val dataSourceBuilder1 = DataSourceBuilder.create()
            dataSourceBuilder1.driverClassName("com.mysql.cj.jdbc.Driver")
            dataSourceBuilder1.url("jdbc:mysql://192.168.1.151:3306/db3?characterEncoding=UTF-8")
            dataSourceBuilder1.username("dev")
            dataSourceBuilder1.password("1qaz@WSX")

            val dataSourceBuilder2 = DataSourceBuilder.create()
            dataSourceBuilder2.driverClassName("com.mysql.cj.jdbc.Driver")
            dataSourceBuilder2.url("jdbc:mysql://192.168.1.151:3306/db4?characterEncoding=UTF-8")
            dataSourceBuilder2.username("dev")
            dataSourceBuilder2.password("1qaz@WSX")

            val datasource1:DataSource = dataSourceBuilder1.build()
            val datasource2:DataSource = dataSourceBuilder2.build()

            val targetDataSources = mapOf<Any, Any>("primaryDataSource" to datasource1, "secondaryDataSource" to datasource2)
            dataSourceNames.add("primaryDataSource")
            dataSourceNames.add("secondaryDataSource")
            val dataSource = DynamicDataSource().apply {
                this.setTargetDataSources(targetDataSources)
                this.setDefaultTargetDataSource(datasource1)
                this.afterPropertiesSet()
            }
            dataSource
        }
    }

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

//    @Primary
//    @Bean(name = ["dbSessionFactory"])
//    fun dbSessionFactory(): LocalSessionFactoryBean? {
//        val sessionFactoryBean = LocalSessionFactoryBean()
//        sessionFactoryBean.setDataSource(dataSource())
//        sessionFactoryBean.setPackagesToScan("com.zipe")
//        sessionFactoryBean.hibernateProperties = hibernateProperties()
//        return sessionFactoryBean
//    }

    @Bean(name = ["multiTransactionManager"])
    fun multiTransactionManager(): PlatformTransactionManager? {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = multiEntityManager()!!.getObject()
        return transactionManager
    }

}
