package com.zipe.test.service

import com.zipe.service.IUserService
import com.zipe.test.base.TestBase
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Mono.delay

class UserServiceTest : TestBase() {

    @Autowired
    lateinit var userService: IUserService

    @Test
    fun `test get user by login id`() {
        var user = userService.findUserByLoginId("admin")
//        Thread.sleep(20000)
//        user = userService.findUserByLoginId("test")
        println(user)
    }
}
