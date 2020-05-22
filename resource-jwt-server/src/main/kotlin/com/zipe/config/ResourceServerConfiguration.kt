package com.zipe.config

import org.apache.commons.io.IOUtils
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.nio.charset.StandardCharsets.UTF_8

@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties::class)
class ResourceServerConfiguration(private val securityProperties: SecurityProperties) : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.requestMatchers().antMatchers("/**")
            .and().authorizeRequests()
//            .antMatchers("/test/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenStore(tokenStore())
    }

    @Bean
    @Primary
    fun tokenServices(): AuthorizationServerTokenServices {
        return DefaultTokenServices().apply {
            this.setSupportRefreshToken(true)
            this.setTokenStore(tokenStore())
//            this.setClientDetailsService(customClientDetailService())
            this.setTokenEnhancer(tokenEnhancer())
        }
    }

    @Bean
    fun tokenStore(): TokenStore = JwtTokenStore(tokenEnhancer())

    @Bean
    fun tokenEnhancer(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setVerifierKey(publicKeyAsString)
        return converter
    }

    internal val publicKeyAsString = IOUtils.toString(securityProperties.jwt.publicKey.inputStream, UTF_8)

}
