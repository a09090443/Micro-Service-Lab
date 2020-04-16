package com.zipe.config

import org.springframework.security.oauth2.common.*
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
//            token.refreshToken = refreshToken


//            authentication ?: token.additionalInformation.run {
//                mutableMapOf<String, Any?>("client_id" to authentication?.oAuth2Request?.clientId)
//            }
//            val additionalInformation =
//                mutableMapOf<String, Any?>("client_id" to authentication?.oAuth2Request?.clientId)

//            token.additionalInformation = additionalInformation
            return token
//            if (authentication != null) {
//                additionalInformation["client_id"] = authentication.oAuth2Request.clientId
//                token.additionalInformation = additionalInformation
//
//                return token
//            }
        }

        return accessToken
    }

    private fun getNewToken(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}
