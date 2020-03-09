package com.zipe.service

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity

interface IUserService {

    fun findUserByLoginId(loginId: String): SysUserEntity

    fun findUserByEmail(email: String): SysUserEntity

    fun saveUserLogonRecord(sysUserLogonLogEntity: SysUserLogonLogEntity)

}
