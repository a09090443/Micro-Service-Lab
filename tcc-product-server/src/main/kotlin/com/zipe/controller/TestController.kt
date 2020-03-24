package com.zipe.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class TestController {

    @GetMapping("/test")
    fun test(): String{
        return "test"
    }
}
