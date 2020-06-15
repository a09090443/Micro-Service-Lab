package com.zipe.grant

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAuthenticationToken(authorities: Collection<GrantedAuthority>?, username: Any?) :
    AbstractAuthenticationToken(authorities) {

    private val principal = username

    override fun getPrincipal(): Any? {
        return this.principal
    }

    override fun getCredentials(): Any? {
        return null
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
        require(!isAuthenticated) { "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead" }
        super.setAuthenticated(false)
    }

}
