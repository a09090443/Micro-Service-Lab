package com.zipe.config

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory

@Configuration
class DataStoreConfig {

    @Autowired
    private lateinit var entityManagerFactory: EntityManagerFactory

    @Bean
    fun sessionFactory(): SessionFactory {
        if (entityManagerFactory.unwrap(SessionFactory::class.java) == null) {
            throw NullPointerException("factory is not a hibernate factory")
        }
        return entityManagerFactory.unwrap(SessionFactory::class.java)
    }
}
