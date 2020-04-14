package com.zipe.test.repository

import com.zaxxer.hikari.HikariDataSource
import com.zipe.entity.SysUserEntity
import com.zipe.repository.ISysUserRepository
import com.zipe.test.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource

class SysUserRepositoryTest : TestBase() {

    @Autowired
    lateinit var sysUserRepository: ISysUserRepository

    @Autowired
    lateinit var db1DataSource: DataSource

    @Test
    fun getAllUsers() {
        if(db1DataSource is HikariDataSource){
            println(db1DataSource)
        }else{
            println("GG")
        }
        val sysUserList: MutableList<SysUserEntity> = sysUserRepository.findAll() as MutableList<SysUserEntity>
        println(sysUserList)
    }
}
