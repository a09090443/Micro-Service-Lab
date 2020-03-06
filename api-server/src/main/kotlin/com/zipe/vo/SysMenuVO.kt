package com.zipe.vo

data class SysMenuVO(
    var menuId: Int = 0,
    var name: String = "",
    var link: String = "",
    var orderId: Int = 0,
    var sub: MutableList<SysMenuVO> = mutableListOf()
)
