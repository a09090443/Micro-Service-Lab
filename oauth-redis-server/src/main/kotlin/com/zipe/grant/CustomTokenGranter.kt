package com.zipe.grant

import org.springframework.security.oauth2.provider.*
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices

class CustomTokenGranter(
    tokenServices: AuthorizationServerTokenServices,
    clientDetailsService: ClientDetailsService, requestFactory: OAuth2RequestFactory,
    grantType: String = GRANT_TYPE
) : AbstractTokenGranter(tokenServices, clientDetailsService, requestFactory, grantType) {

    companion object {
        const val GRANT_TYPE = "custom"
    }

    override fun getOAuth2Authentication(
        client: ClientDetails?,
        tokenRequest: TokenRequest?
    ): OAuth2Authentication {
        val parameters = tokenRequest?.requestParameters
        val username = parameters?.get("username")
        val userAuth = CustomAuthenticationToken(null, username)
        userAuth.details = parameters

        val storedOAuth2Request = requestFactory.createOAuth2Request(client, tokenRequest)
        return OAuth2Authentication(storedOAuth2Request, userAuth)
    }
}
