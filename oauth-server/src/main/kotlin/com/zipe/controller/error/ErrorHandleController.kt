package com.zipe.controller.error

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ErrorHandleController : ErrorController {
    @RequestMapping("/error")
    fun handleError(): String? { //do something like logging
        return "404"
    }

    override fun getErrorPath(): String? {
        return "/error"
    }
}
