package com.zipe.repository

import com.zipe.entity.SysUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ISysUserRepository : JpaRepository<SysUserEntity, Long> {
}
