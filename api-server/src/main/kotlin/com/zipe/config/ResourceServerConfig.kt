package com.zipe.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.token.RemoteTokenServices

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Value("\${security.oauth2.resource.token-info-uri}")
    private val tokenInfoUri: String? = null

    @Value("\${security.oauth2.client.access-token-uri}")
    private val accessTokenUri: String? = null

    @Value("\${security.oauth2.client.client-id}")
    private val clientId: String? = null

    @Value("\${security.oauth2.client.client-secret}")
    private val clientSecret: String? = null

    @Value("\${security.oauth2.client.user-authorization-uri}")
    private val userAuthorizationUri: String? = null

    @Value("\${security.oauth2.resource.id}")
    private val RESOURCE_ID: String? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.anonymous().disable()
            .requestMatchers().antMatchers("/**")
            .and().authorizeRequests()
            .antMatchers("/jwt1/**").hasRole("ADMIN")
            .antMatchers("/jwt2/**").authenticated()
            .antMatchers("/jwt3/**").access("#oauth2.hasScope('read')")
            .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        val tokenServices = RemoteTokenServices()
        tokenServices.setCheckTokenEndpointUrl(tokenInfoUri)
        tokenServices.setClientId(clientId)
        tokenServices.setClientSecret(clientSecret)
        resources.resourceId(RESOURCE_ID).tokenServices(tokenServices)
    }
}
