package com.zipe.repository.system

import com.zipe.entity.system.SysUserLogonLogEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ISysUserLogonLogRepository : CrudRepository<SysUserLogonLogEntity, Long> {

    fun findByLoginId(loginId: String): SysUserLogonLogEntity

}
