package com.zipe.controller

import com.zipe.entity2.CurrentStatusEntity
import com.zipe.repository2.ICurrentStatusRepository
import com.zipe.service.impl.UserServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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
