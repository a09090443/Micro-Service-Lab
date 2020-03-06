package com.zipe.service

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity

interface IUserService {

    fun findAllUsers(): MutableList<SysUserEntity>

    fun findUserByLoginId(loginId: String): SysUserEntity

    fun findUserByEmail(email: String): SysUserEntity

    fun findMaxLoginId(): SysUserEntity

    fun saveUser(sysUserEntity: SysUserEntity)

    fun saveUserLogonRecord(sysUserLogonLogEntity: SysUserLogonLogEntity)

}
