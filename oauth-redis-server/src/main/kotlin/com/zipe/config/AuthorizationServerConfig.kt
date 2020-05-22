package com.zipe.config

import com.zipe.grant.CustomTokenGranter
import com.zipe.security.service.CustomClientDetailService
import com.zipe.security.service.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.CompositeTokenGranter
import org.springframework.security.oauth2.provider.OAuth2RequestFactory
import org.springframework.security.oauth2.provider.TokenGranter
import org.springframework.security.oauth2.provider.TokenRequest
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
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

    @Value("\${config.oauth2.privateKey}")
    private val privateKey: String? = null

    @Value("\${config.oauth2.publicKey}")
    private val publicKey: String? = null

    @Bean
    fun jdbcClientDetailsService(): JdbcClientDetailsService {
        val details = JdbcClientDetailsService(dataSource)
        details.setPasswordEncoder(passwordEncoder)
        return details
    }

    @Bean
    fun customClientDetailService(): CustomClientDetailService {
        return CustomClientDetailService()
    }

//    @Bean
//    fun tokenStore(): TokenStore {
////        return JdbcTokenStore(dataSource)
//        val redisTokenStore = RedisTokenStore(connectionFactory)
//        redisTokenStore.setPrefix("auth-token:")
//        return redisTokenStore
//    }

    @Bean
    fun tokenEnhancer(): JwtAccessTokenConverter? {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey(privateKey)
        converter.setVerifierKey(publicKey)
        return converter
    }

    @Bean
    fun tokenStore(): JwtTokenStore? {
        return JwtTokenStore(tokenEnhancer())
    }

    @Bean
    fun approvalStore(): ApprovalStore {
        return JdbcApprovalStore(dataSource)
    }

    @Bean
    fun authorizationCodeServices(): AuthorizationCodeServices {
        return JdbcAuthorizationCodeServices(dataSource)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
//		security.allowFormAuthenticationForClients()
//		security.tokenKeyAccess("permitAll()")
//		security.checkTokenAccess("isAuthenticated()")
        security.checkTokenAccess("permitAll()")
        security.allowFormAuthenticationForClients()
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(customClientDetailService())
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenServices(tokenServices())
            .approvalStore(approvalStore())
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .authorizationCodeServices(authorizationCodeServices())
//            .tokenEnhancer(tokenEnhancer())
//            .tokenStore(tokenStore())
            .tokenGranter(tokenGranter())
    }

    private fun requestFactory(): OAuth2RequestFactory {
        return DefaultOAuth2RequestFactory(customClientDetailService())
    }

    @Bean
    @Primary
    fun tokenServices(): AuthorizationServerTokenServices {
        //      将增强的token设置到增强链中
        val enhancerChain = TokenEnhancerChain().apply {
            this.setTokenEnhancers(listOf(CustomTokenEnhancer(), tokenEnhancer()))
        }
        return DefaultTokenServices().apply {
            this.setSupportRefreshToken(true)
            this.setTokenStore(tokenStore())
//            this.setClientDetailsService(customClientDetailService())
            this.setTokenEnhancer(enhancerChain)
        }
    }

    private fun getDefaultTokenGranters(): List<TokenGranter?>? {
        val tokenServices: AuthorizationServerTokenServices = tokenServices()
        val authorizationCodeServices = authorizationCodeServices()
        val requestFactory = requestFactory()

        val tokenGranters = mutableListOf<TokenGranter>()
        // 添加授权码模式
        // 添加授权码模式
        tokenGranters.add(
            AuthorizationCodeTokenGranter(
                tokenServices,
                authorizationCodeServices,
                customClientDetailService(),
                requestFactory
            )
        )
        // 添加刷新令牌的模式
        // 添加刷新令牌的模式
        tokenGranters.add(RefreshTokenGranter(tokenServices, customClientDetailService(), requestFactory))
        // 添加隐式授权模式
        // 添加隐式授权模式
        tokenGranters.add(ImplicitTokenGranter(tokenServices, customClientDetailService(), requestFactory))
        // 添加客户端模式
        // 添加客户端模式
        tokenGranters.add(ClientCredentialsTokenGranter(tokenServices, customClientDetailService(), requestFactory))
        // 添加自定义授权模式（实际是密码模式的复制）
        // 添加自定义授权模式（实际是密码模式的复制）
        tokenGranters.add(CustomTokenGranter(tokenServices, customClientDetailService(), requestFactory))
        if (authenticationManager != null) {
            // 添加密码模式
            tokenGranters.add(
                ResourceOwnerPasswordTokenGranter(
                    authenticationManager,
                    tokenServices,
                    customClientDetailService(),
                    requestFactory
                )
            )
        }
        return tokenGranters
    }

    private fun tokenGranter(): TokenGranter? {
//        log.info("OAuth2AuthorizationServerConfigure.tokenGranter")
        return object : TokenGranter {
            private var delegate: CompositeTokenGranter? = null
            override fun grant(grantType: String?, tokenRequest: TokenRequest?): OAuth2AccessToken? {
                if (delegate == null) {
                    delegate = CompositeTokenGranter(getDefaultTokenGranters())
                }
                return delegate?.grant(grantType, tokenRequest)
            }
        }
    }
}
