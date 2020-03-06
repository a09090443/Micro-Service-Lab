package com.zipe.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    private val log: Logger = LoggerFactory.getLogger(LoginController::class.java)

    @GetMapping("/")
    fun home(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

}
