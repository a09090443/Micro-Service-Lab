package com.zipe.security.service

import com.zipe.repository.IOauthAccountRepository
import com.zipe.repository.ISysUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.client.BaseClientDetails

class CustomClientDetailService : ClientDetailsService {

    @Autowired
    private lateinit var oauthAccountRepository: IOauthAccountRepository

    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return oauthAccountRepository.findByClientId(clientId).run {

            val resourceIds = this.resourceIds
            val scopes = this.scope
            val grantTypes = this.authorizedGrantTypes
            val authorities = this.authorities

            val clientDetails = BaseClientDetails(this.clientId, resourceIds, scopes, grantTypes, authorities).also {
                it.clientId = this.clientId
                it.clientSecret = this.clientSecret
                it.accessTokenValiditySeconds = this.accessTokenValidity
                it.refreshTokenValiditySeconds = this.refreshTokenValidity
    //                it.autoApproveScopes = this.scope?.toSet()
            }
            clientDetails.clientId = this.clientId

            clientDetails
        }
    }
}
