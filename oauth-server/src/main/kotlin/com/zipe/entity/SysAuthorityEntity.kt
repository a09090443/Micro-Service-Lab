package com.zipe.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "sys_authority")
data class SysAuthorityEntity(

    @Id
    @NotEmpty
    @Column(name = "authority_id", nullable = false, length = 2, unique = true)
    val authorityId: String = "",

    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    val name: String = ""
)
