package com.zipe.service

import com.zipe.entity.system.SysUserEntity
import com.zipe.entity.system.SysUserLogonLogEntity
import com.zipe.vo.SysUserVO

interface IUserService {

    fun findAllUsers(): MutableList<SysUserEntity>

    fun findUserByLoginId(loginId: String): SysUserEntity

    fun findUserByEmail(email: String): SysUserEntity

    fun findMaxLoginId(): SysUserEntity

    fun saveUser(sysUserVO: SysUserVO)

    fun delUser(loginId: String)

    fun saveUserLogonRecord(sysUserLogonLogEntity: SysUserLogonLogEntity)

}
