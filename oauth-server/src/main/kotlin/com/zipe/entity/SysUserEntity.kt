package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "sys_user")
data class SysUserEntity(

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Id
    @Basic
    @Column(name = "user_id", nullable = false, length = 6, unique = true)
    val userId: String = "",

    @Basic
    @Column(name = "login_id", updatable = false, nullable = false, length = 50)
    val loginId: String = "",

    @Basic
    @Column(name = "password", updatable = false, nullable = false, length = 500)
    @NotNull
    @Size(max = 80)
    val password: String = "",

    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    val firstName: String = "",

    @Basic
    @Column(name = "last_name", length = 50)
    val lastName: String = "",

    @Basic
    @Column(name = "email", updatable = false, nullable = false, length = 50)
    @Email
    @Size(max = 50)
    val email: String = "",

    @Basic
    @Column(name = "phone", length = 10)
    @Pattern(regexp = "(^$|[0-9]{10})")
    val phone: String = "",

    @Basic
    @Column(name = "address", length = 40)
    val address: String = "",

    @Basic
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthday: String = "",

    @Basic
    @Column(name = "image", length = 20)
    val image: String = "",

    @Basic
    @Column(name = "activated")
    val activated: Boolean = false,

    @Basic
    @Column(name = "last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val lastLoginTime: Date = Date(),

    @Basic
    @Column(name = "register_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val registerTime: Date = Date()

)
