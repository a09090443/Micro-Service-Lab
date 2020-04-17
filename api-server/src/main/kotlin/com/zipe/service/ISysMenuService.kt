package com.zipe.service

import com.zipe.entity.system.SysMenuEntity
import com.zipe.vo.SysMenuVO

interface ISysMenuService {
    fun findAllSysMenu(): List<SysMenuEntity>

    fun findSysMenuTree(): List<SysMenuVO>
}
