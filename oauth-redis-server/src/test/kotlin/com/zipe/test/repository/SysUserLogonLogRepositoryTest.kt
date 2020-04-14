package com.zipe.test.repository

import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.repository.ISysUserLogonLogRepository
import com.zipe.test.base.TestBase
import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class SysUserLogonLogRepositoryTest : TestBase() {

    @Autowired
    lateinit var sysUserLogonLogRepository: ISysUserLogonLogRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Ignore
    @Test
    fun saveUserLog(){
    val sysUserLogonLogEntity = SysUserLogonLogEntity()
        sysUserLogonLogEntity.loginId = "test"
        sysUserLogonLogEntity.loginTime = Date()
        sysUserLogonLogEntity.status = "login"
        sysUserLogonLogRepository.save(sysUserLogonLogEntity)
    }

//    @Ignore
    @Test
    fun getAllUsersLog() {
        println(passwordEncoder.encode("secret"))

        val sysUserLogonLogList: MutableList<SysUserLogonLogEntity> = sysUserLogonLogRepository.findAll() as MutableList<SysUserLogonLogEntity>
        println(sysUserLogonLogList)
    }
}
