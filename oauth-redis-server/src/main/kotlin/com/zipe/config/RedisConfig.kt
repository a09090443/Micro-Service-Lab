package com.zipe.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
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
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {

//    @Bean
//    fun redisConnectionFactory(): JedisConnectionFactory = JedisConnectionFactory()

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, String> {

        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.setConnectionFactory(factory)
        return redisTemplate
    }

    @Bean
    fun cacheManager(factory: RedisConnectionFactory): CacheManager {

        val pair = RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer())
        val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair).entryTtl(
            Duration.ofHours(1)
        )

        val initialCacheConfiguration = mapOf<String, RedisCacheConfiguration?>(
            "demoCache" to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1)),
            "userCache" to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5))
        )

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
            .cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(initialCacheConfiguration).build()
    }
}
