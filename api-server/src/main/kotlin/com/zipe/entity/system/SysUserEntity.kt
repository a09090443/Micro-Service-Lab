package com.zipe.entity.system

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
    var id: Long = 0,

    @Id
    @Basic
    @Column(name = "user_id", nullable = false, length = 6, unique = true)
    var userId: String = "",

    @Basic
    @Column(name = "login_id", updatable = false, nullable = false, length = 50)
    var loginId: String = "",

    @Basic
    @Column(name = "password", updatable = false, nullable = false, length = 500)
    @NotNull
    @Size(max = 80)
    var password: String = "",

    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    var firstName: String = "",

    @Basic
    @Column(name = "last_name", length = 50)
    var lastName: String = "",

    @Basic
    @Column(name = "email", updatable = false, nullable = false, length = 50)
    @Email
    @Size(max = 50)
    var email: String = "",

    @Basic
    @Column(name = "phone", length = 10)
    @Pattern(regexp = "(^$|[0-9]{10})")
    var phone: String = "",

    @Basic
    @Column(name = "address", length = 40)
    var address: String = "",

    @Basic
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthday: String = "",

    @Basic
    @Column(name = "image", length = 20)
    var image: String = "",

    @Basic
    @Column(name = "activated")
    var activated: Boolean = false,

    @Basic
    @Column(name = "last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var lastLoginTime: Date = Date(),

    @Basic
    @Column(name = "register_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var registerTime: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_user_authority_mapping",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
    var authority: Set<SysAuthorityEntity> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_user_title_mapping",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "title_id")]
    )
    var sysUserTitle: Set<SysUserTitleEntity> = mutableSetOf()

)
