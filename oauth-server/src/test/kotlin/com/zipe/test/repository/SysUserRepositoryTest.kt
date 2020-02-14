package com.zipe.test.repository

import com.zipe.entity.SysUserEntity
import com.zipe.repository.ISysUserRepository
import com.zipe.test.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysUserRepositoryTest : TestBase() {

    @Autowired
    lateinit var sysUserRepository: ISysUserRepository

    @Test
    fun getAllUsers() {
        val sysUserList: MutableList<SysUserEntity> = sysUserRepository.findAll()
        println(sysUserList);
    }
}
