package com.zipe.controller

import com.zipe.base.controller.BaseController
import com.zipe.service.ISysMenuService
import com.zipe.vo.SysMenuVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class SysMenuController : BaseController() {
    private val log: Logger = LoggerFactory.getLogger(SysUserController::class.java)

    @Autowired
    lateinit var sysMenuService: ISysMenuService

    @GetMapping("/sysMenuTree")
    fun getMenuTree(): MutableMap<String, List<SysMenuVO>> {
        val list: List<SysMenuVO> = sysMenuService.findSysMenuTree()

        val map: MutableMap<String, List<SysMenuVO>> = mutableMapOf()
        map["menu"] = list
        return map
    }

}
