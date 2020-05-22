package com.zipe.security.service

import com.zipe.repository.IOauthAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.client.BaseClientDetails

class CustomClientDetailService : ClientDetailsService {

    @Autowired
    private lateinit var oauthAccountRepository: IOauthAccountRepository

    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return oauthAccountRepository.findByClientId(clientId).run {

            val clientDetails = BaseClientDetails(
                this.clientId,
                this.resourceIds,
                this.scope,
                this.authorizedGrantTypes,
                this.authorities
            ).also {
                it.clientId = this.clientId
                it.clientSecret = this.clientSecret
                it.accessTokenValiditySeconds = this.accessTokenValidity
                it.refreshTokenValiditySeconds = this.refreshTokenValidity
                it.registeredRedirectUri = this.webServerRedirectUri?.split(",")?.toSet()
                //                it.autoApproveScopes = this.scope?.toSet()
            }
            clientDetails.clientId = this.clientId

            clientDetails
        }
    }
}
