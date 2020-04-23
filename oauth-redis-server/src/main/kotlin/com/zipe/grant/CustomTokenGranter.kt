package com.zipe.grant

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.OAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices

class CustomTokenGranter(
    tokenServices: AuthorizationServerTokenServices,
    clientDetailsService: ClientDetailsService,
    requestFactory: OAuth2RequestFactory,
    grantType: String
) : AbstractTokenGranter(tokenServices, clientDetailsService, requestFactory, grantType) {

    private val GRANT_TYPE = "custom_code"
//    private val authenticationManager: AuthenticationManager
}
