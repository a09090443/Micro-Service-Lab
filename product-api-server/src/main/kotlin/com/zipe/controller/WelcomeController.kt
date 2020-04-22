package com.zipe.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class WelcomeController {

    @GetMapping(value = ["/test"])
    fun test(): String {
        return "test"
    }
}
