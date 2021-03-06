package com.zipe.controller

import com.zipe.base.controller.BaseController
import com.zipe.util.common.UserInfoUtil
import org.springframework.hateoas.MediaTypes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test", produces = [MediaTypes.HAL_JSON_VALUE])
class TestController : BaseController() {

    @GetMapping(value = ["/test"])
    fun test(): ResponseEntity<String> {

        val userInfo = UserInfoUtil.getUserInfo()
        println(this.getPrincipal())
        return "test".let { ResponseEntity.ok(it) }
    }
}
