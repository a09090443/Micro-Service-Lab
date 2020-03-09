package com.zipe.vo

import java.util.*

data class SysUserVO(

    var id: Long = 0,

    var userId: String = "",

    var loginId: String = "",

    var password: String = "",

    var firstName: String = "",

    var lastName: String = "",

    var email: String = "",

    var phone: String = "",

    var address: String = "",

    var birthday: String = "",

    var image: String = "",

    var activated: Boolean = false,

    var lastLoginTime: Date = Date(),

    var registerTime: Date = Date()
)
