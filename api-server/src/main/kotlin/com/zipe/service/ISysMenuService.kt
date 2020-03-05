package com.zipe.service

import com.zipe.SysMenuVO
import com.zipe.entity.SysMenuEntity

interface ISysMenuService {
    fun findAllSysMenu(): List<SysMenuEntity>

    fun findSysMenuTree(): List<SysMenuVO?>
}
