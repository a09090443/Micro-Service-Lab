package com.zipe.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/test/{id}")
    fun test(@PathVariable("id") id: String): String {
        return id
    }
}
