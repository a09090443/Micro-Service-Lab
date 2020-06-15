package com.zipe.controller

import com.zipe.util.log.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    val logger = logger()

    @GetMapping(value = ["/test"])
    fun test(): String = "test"
}
