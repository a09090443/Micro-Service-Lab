package com.zipe.exception

import org.springframework.security.core.AuthenticationException

class UserNotActivatedException : AuthenticationException {

    constructor(msg: String) : super(msg)

    constructor(msg: String, t: Throwable) : super(msg, t)

}
