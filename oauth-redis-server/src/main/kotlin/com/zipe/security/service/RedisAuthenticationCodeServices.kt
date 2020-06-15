package com.zipe.security.service


import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.oauth2.common.util.SerializationUtils
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.nio.charset.StandardCharsets

const val AUTH_CODE_KEY = "oauth:code"

@Service
class RedisAuthenticationCodeServices(connectionFactory: RedisConnectionFactory) : RandomValueAuthorizationCodeServices() {
    private val connectionFactory: RedisConnectionFactory
    private val connection: RedisConnection = connectionFactory.connection

    override fun store(code: String, authentication: OAuth2Authentication) {
        val conn = connection
        try {
            conn.hSet(
                AUTH_CODE_KEY.toByteArray(StandardCharsets.UTF_8),
                code.toByteArray(StandardCharsets.UTF_8),
                SerializationUtils.serialize(authentication)
            )
        } catch (e: Exception) {
            conn.close()
        }
    }

    override fun remove(code: String): OAuth2Authentication? {
        val conn = connection
        try {
            var authentication: OAuth2Authentication? = null
            try {
                authentication = SerializationUtils
                    .deserialize(
                        conn.hGet(
                            AUTH_CODE_KEY.toByteArray(StandardCharsets.UTF_8),
                            code.toByteArray(StandardCharsets.UTF_8)
                        )
                    )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (authentication != null) {
                conn.hDel(
                    AUTH_CODE_KEY.toByteArray(StandardCharsets.UTF_8), code.toByteArray(StandardCharsets.UTF_8)
                )
            }
            return authentication!!
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn.close()
        }
        return null
    }

    init {
        Assert.notNull(connectionFactory, "RedisConnectionFactory required")
        this.connectionFactory = connectionFactory
    }
}
