package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "oauth_account")
data class OauthAccountEntity(

    @Id
    @NotEmpty
    @Column(name = "client_id", nullable = false, length = 256, unique = true)
    val clientId: String = "",

    @Column(name = "resource_ids", nullable = true, length = 256)
    val resourceIds: String = "",

    @Column(name = "client_secret", nullable = true, length = 256)
    val clientSecret: String = "",

    @Column(name = "scope", nullable = true, length = 256)
    val scope: String = "",

    @Column(name = "authorized_grant_types", nullable = true, length = 256)
    val authorizedGrantTypes: String = "",

    @Column(name = "web_server_redirect_uri", nullable = true, length = 256)
    val webServerRedirectUri: String? = "",

    @Column(name = "authorities", nullable = true, length = 256)
    val authorities: String = "",

    @Column(name = "access_token_validity", nullable = true, length = 11)
    val accessTokenValidity: Int? = 0,

    @Column(name = "refresh_token_validity", nullable = true, length = 11)
    val refreshTokenValidity: Int? = 0,

    @Column(name = "additional_information", nullable = true, length = 4096)
    val additionalInformation: String = "",

    @Column(name = "autoapprove", nullable = true, length = 256)
    val autoapprove: String = "",

    @Column(name = "update_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val updateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_user", nullable = true, length = 256)
    val updateBy: String = "",

    @Column(name = "create_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val createTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_user", nullable = true, length = 256)
    val createBy: String = ""
) : Serializable
