package com.zipe.controller.error

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.NoHandlerFoundException

@Controller
class ErrorHandleController : ErrorController {
    @RequestMapping("/404")
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundError(): String { //do something like logging
        return "404"
    }

    @RequestMapping("/error")
    fun errorPage(): String {
        return "/login"
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}
