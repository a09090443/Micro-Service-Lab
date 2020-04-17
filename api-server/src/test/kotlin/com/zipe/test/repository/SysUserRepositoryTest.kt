package com.zipe.test.repository

import com.zipe.entity.system.SysMenuEntity
import com.zipe.repository.system.ISysMenuRepository
import com.zipe.test.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysUserRepositoryTest : TestBase() {

    @Autowired
    lateinit var sysMenuRepository: ISysMenuRepository

    @Test
    fun getAllSysMenu() {
        val sysMenuList: MutableList<SysMenuEntity> = sysMenuRepository.findAll() as MutableList<SysMenuEntity>
        println(sysMenuList.size)
    }
}
