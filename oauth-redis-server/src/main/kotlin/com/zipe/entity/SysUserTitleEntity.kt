package com.zipe.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

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
