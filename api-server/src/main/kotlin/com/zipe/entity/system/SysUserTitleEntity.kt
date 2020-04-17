package com.zipe.entity.system

import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name = "sys_user_title")
data class SysUserTitleEntity(
    @Id
    @NotEmpty
    @Column(name = "title_id", nullable = false, length = 2, unique = true)
    val titleId: String = "",

    @Column(name = "title_name", length = 10)
    val titleName: String = ""
)
