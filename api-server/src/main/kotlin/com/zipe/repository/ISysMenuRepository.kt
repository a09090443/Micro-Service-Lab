package com.zipe.repository

import com.zipe.entity.SysMenuEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository("sysMenuRepository")
interface ISysMenuRepository : CrudRepository<SysMenuEntity, Long> {

    @Query("SELECT S.menuName FROM SysMenuEntity S WHERE S.parentId = 0")
    fun findAllByLevel(): List<SysMenuEntity>
}
