package com.zipe.repository

import com.zipe.entity.OauthAccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOauthAccountRepository : JpaRepository<OauthAccountEntity, Long> {
    fun findByClientId(clientId: String?): OauthAccountEntity
}
