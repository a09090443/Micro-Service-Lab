package com.zipe.config

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2RefreshToken
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

            authentication ?: token.additionalInformation.run {
                mutableMapOf<String, Any?>("client_id" to authentication?.oAuth2Request?.clientId)
            }

            return token
//            val additionalInformation = mutableMapOf<String, Any>().let {
//                it["client_id"] = accessToken..clientId
//                token.additionalInformation = additionalInformation
//                return token
//            }
//            authentication?.let {
//                additionalInformation["client_id"] = it.oAuth2Request.clientId
//                token.additionalInformation = additionalInformation
//                return token
//            }
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
