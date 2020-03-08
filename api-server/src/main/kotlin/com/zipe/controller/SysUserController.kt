package com.zipe.controller

import com.zipe.base.controller.BaseController
import com.zipe.entity.SysUserEntity
import com.zipe.service.IUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class SysUserController : BaseController() {
    private val log: Logger = LoggerFactory.getLogger(SysUserController::class.java)

    @Autowired
    private lateinit var userService: IUserService

    @GetMapping("/{loginId}")
    fun getUser(@PathVariable(value = "loginId") loginId: String): SysUserEntity {
        return userService.findUserByLoginId(loginId)
    }

    @PostMapping
    fun addUser(@RequestBody sysUserEntity: SysUserEntity): ResponseEntity<String> {
        userService.saveUser(sysUserEntity)
        return ResponseEntity.ok("Add User Success")
    }

    @DeleteMapping("/del/{loginId}")
    fun delUser(@PathVariable loginId: String): ResponseEntity<String> {
        userService.delUser(loginId)
        return ResponseEntity.ok("Delete User Success")
    }
}