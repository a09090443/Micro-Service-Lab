package com.zipe.service.impl

import com.zipe.entity.SysUserEntity
import com.zipe.repository.ISysUserRepository
import com.zipe.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service("userService")
class UserServiceImpl : UserService {

    @Autowired
    lateinit var sysUserRepository: ISysUserRepository

    override fun getAllUsers(): List<SysUserEntity> {
        return sysUserRepository.findAll()
    }

    override fun createNewUser(user: SysUserEntity): SysUserEntity {
        return sysUserRepository.save(user)
    }

    override fun getUserById(userId: Long): ResponseEntity<SysUserEntity> {
        return sysUserRepository.findById(userId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    override fun updateUserById(userId: Long, newUser: SysUserEntity): ResponseEntity<SysUserEntity> {
        return sysUserRepository.findById(userId).map { existingUser ->
            val updatedUser: SysUserEntity = existingUser
                .copy(
                    registerTime = Timestamp(System.currentTimeMillis()),
                    loginId = newUser.loginId,
                    password = newUser.password
                )
            ResponseEntity.ok().body(sysUserRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    override fun deleteUserById(userId: Long): ResponseEntity<Void> {
        return sysUserRepository.findById(userId).map { user ->
            sysUserRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}
