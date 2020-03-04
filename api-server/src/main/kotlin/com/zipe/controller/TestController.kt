package com.zipe.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class TestController {

    @GetMapping("/jwt1")
    fun jwt1(): String {
        return "jwt1"
    }

    @GetMapping("/jwt2")
    fun jwt2(): String {
        return "jwt2"
    }

    @GetMapping("/jwt3")
    fun jwt3(): String {
        return "jwt3"
    }

    @GetMapping("/jwt4")
    fun jwt4(): String {
        return "jwt4"
    }
}
