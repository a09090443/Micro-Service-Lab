package com.zipe.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.support.NoOpCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration


@Configuration
@EnableConfigurationProperties(CacheProperties::class)
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    @Autowired
    lateinit var cacheProperties: CacheProperties

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, String> {

        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.setConnectionFactory(factory)
        return redisTemplate
    }

    @Bean
    fun cacheManager(factory: RedisConnectionFactory): CacheManager {

        if (cacheProperties.enable) {
            val pair = RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer())
            val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair).entryTtl(
                Duration.ofHours(1)
            )

            val initialCacheConfiguration = cacheProperties.key.map { (k, v) ->
                k to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.parse(v))
            }.toMap()

            return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(initialCacheConfiguration).build()
        } else {
            return NoOpCacheManager()
        }

    }

}
