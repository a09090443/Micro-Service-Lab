package com.zipe.config

import com.zipe.security.service.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private lateinit var dataSource: DataSource

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private val connectionFactory: RedisConnectionFactory? = null

    @Bean
    fun jdbcClientDetailsService(): JdbcClientDetailsService {
        val details = JdbcClientDetailsService(dataSource)
        details.setPasswordEncoder(passwordEncoder)
        return details
    }

    @Bean
    fun tokenStore(): TokenStore {
//        return JdbcTokenStore(dataSource)
        val redisTokenStore = RedisTokenStore(connectionFactory)
        redisTokenStore.setPrefix("auth-token:")
        return redisTokenStore
    }

    @Bean
    fun approvalStore(): ApprovalStore {
        return JdbcApprovalStore(dataSource)
    }

    @Bean
    fun authorizationCodeServices(): AuthorizationCodeServices {
        return JdbcAuthorizationCodeServices(dataSource)
    }

    @Bean
    fun tokenEnhancer(): TokenEnhancer {
        return CustomTokenEnhancer()
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
//		security.allowFormAuthenticationForClients()
//		security.tokenKeyAccess("permitAll()")
//		security.checkTokenAccess("isAuthenticated()")
        security.checkTokenAccess("permitAll()")
        security.allowFormAuthenticationForClients()
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(jdbcClientDetailsService())
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .approvalStore(approvalStore())
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .authorizationCodeServices(authorizationCodeServices())
            .tokenEnhancer(tokenEnhancer())
            .tokenStore(tokenStore())
    }
}
