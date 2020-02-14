package com.zipe.service

import com.zipe.entity.SysUserEntity
import org.springframework.http.ResponseEntity

interface UserService {

    fun getAllUsers(): List<SysUserEntity>
    fun createNewUser(user: SysUserEntity): SysUserEntity
    fun getUserById(userId: Long): ResponseEntity<SysUserEntity>
    fun updateUserById(userId: Long, newUser: SysUserEntity): ResponseEntity<SysUserEntity>
    fun deleteUserById(userId: Long): ResponseEntity<Void>
}
