package com.zipe.repository.system

import com.zipe.entity.system.SysMenuEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("sysMenuRepository")
interface ISysMenuRepository : JpaRepository<SysMenuEntity, Long> {

    @Query("SELECT S.menuName FROM SysMenuEntity S WHERE S.parentId = 0")
    fun findAllByLevel(): List<SysMenuEntity>
}
