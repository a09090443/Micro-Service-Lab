package com.zipe.controller

import com.zipe.utils.log.logger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    val logger = logger()

    @GetMapping("/")
    fun home(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

}
