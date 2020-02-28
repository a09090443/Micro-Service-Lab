package com.zipe.controller.Exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
@ControllerAdvice
class GlobalExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    @Throws(Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): Any? {
        log.error(
            "---DefaultException Handler---Host {} invokes url {} ERROR: {}",
            req.remoteHost,
            req.requestURL,
            e.message
        )
        return e.message
    }

}
