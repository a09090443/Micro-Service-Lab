package com.zipe.config

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import java.util.*

class CustomTokenEnhancer : TokenEnhancer {
    override fun enhance(accessToken: OAuth2AccessToken?, authentication: OAuth2Authentication?): OAuth2AccessToken? {
        if (accessToken is DefaultOAuth2AccessToken) {

            val token: DefaultOAuth2AccessToken = accessToken
            token.value = getNewToken()

            token.refreshToken?.let {
                DefaultOAuth2RefreshToken(getNewToken())
            }

            authentication?.let {
                token.additionalInformation = mapOf("client_id" to authentication.oAuth2Request.clientId)
            }

            return token
        }

        return accessToken
    }

    private fun getNewToken(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}
