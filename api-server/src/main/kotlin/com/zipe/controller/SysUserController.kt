package com.zipe.controller

import com.zipe.base.controller.BaseController
import com.zipe.entity.system.SysUserEntity
import com.zipe.service.IUserService
import com.zipe.util.log.logger
import com.zipe.vo.SysUserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class SysUserController : BaseController() {
    val logger = logger()

    @Autowired
    private lateinit var userService: IUserService

    @GetMapping("/{loginId}")
    fun getUser(@PathVariable(value = "loginId") loginId: String): SysUserEntity {
        return userService.findUserByLoginId(loginId)
    }

    @PostMapping
    fun addUser(@RequestBody sysUserVO: SysUserVO): ResponseEntity<String> {
        userService.saveUser(sysUserVO)
        return ResponseEntity.ok("Add User Success")
    }

    @DeleteMapping("/{loginId}")
    fun delUser(@PathVariable loginId: String): ResponseEntity<String> {
        userService.delUser(loginId)
        return ResponseEntity.ok("Delete User Success")
    }
}
