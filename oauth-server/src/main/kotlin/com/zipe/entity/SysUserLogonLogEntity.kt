package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sys_user_logon_log")
data class SysUserLogonLogEntity(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Basic
    @Column(name = "login_id", length = 50)
    var loginId: String = "",

    @Basic
    @Column(name = "status", length = 10)
    var status: String = "",

    @Basic
    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var loginTime: Date = Date()
)
