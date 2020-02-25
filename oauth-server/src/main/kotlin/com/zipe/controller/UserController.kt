package com.zipe.controller

import com.zipe.entity.SysUserEntity
import com.zipe.service.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/ws")
class UserController(private val userService: IUserService) {
//
//    @GetMapping("/users")
//    fun getAllUsers(): List<SysUserEntity> = userService.getAllUsers()
//
//    @PostMapping("/users")
//    fun createNewUser(@Valid @RequestBody user: SysUserEntity): SysUserEntity =
//        userService.createNewUser(user)
//
//    @GetMapping("/users/{id}")
//    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<SysUserEntity> {
//        return userService.getUserById(userId)
//    }
//
//    @PutMapping("/users/{id}")
//    fun updateUserById(
//        @PathVariable(value = "id") userId: Long,
//        @Valid @RequestBody newUser: SysUserEntity
//    ): ResponseEntity<SysUserEntity> {
//        return userService.updateUserById(userId, newUser)
//    }
//
//    @DeleteMapping("/users/{id}")
//    fun deleteUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {
//        return userService.deleteUserById(userId)
//    }
}
