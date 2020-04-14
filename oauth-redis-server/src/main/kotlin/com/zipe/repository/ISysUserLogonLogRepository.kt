package com.zipe.repository

import com.zipe.entity.SysUserLogonLogEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ISysUserLogonLogRepository : CrudRepository<SysUserLogonLogEntity, Long> {

}
