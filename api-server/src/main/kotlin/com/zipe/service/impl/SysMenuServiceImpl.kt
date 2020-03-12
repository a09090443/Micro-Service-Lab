package com.zipe.service.impl

import com.zipe.base.service.BaseService
import com.zipe.entity.SysMenuEntity
import com.zipe.repository.ISysMenuRepository
import com.zipe.service.ISysMenuService
import com.zipe.utils.log.logger
import com.zipe.vo.SysMenuVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service("sysMenuService")
class SysMenuServiceImpl : ISysMenuService, BaseService() {
    val logger = logger()

    @Autowired
    private lateinit var sysMenuRepository: ISysMenuRepository

    override fun findAllSysMenu(): List<SysMenuEntity> {
        return sysMenuRepository.findAll() as MutableList<SysMenuEntity>
    }

    override fun findSysMenuTree(): List<SysMenuVO> {
        val sysMenuEntityList = sysMenuRepository.findAll() as MutableList<SysMenuEntity>
        return getMappingMenu(sysMenuEntityList, 0)
    }

    private fun getMappingMenu(sysMenuEntityList: List<SysMenuEntity>, parantId: Int): MutableList<SysMenuVO> {

        val sysMenuVoList = mutableListOf<SysMenuVO>()
        sysMenuEntityList.forEach {
            if (parantId == it.parentId && it.enabled) {
                val sysMenuVO = SysMenuVO()
                sysMenuVO.menuId = it.menuId
                sysMenuVO.name = it.menuName
                sysMenuVO.orderId = it.orderId
                sysMenuVO.link = it.path
                sysMenuVO.sub = this.getMappingMenu(sysMenuEntityList, it.menuId)
                sysMenuVoList.add(sysMenuVO)
            }
        }
        sysMenuVoList.sortBy { it.orderId }
        return sysMenuVoList
    }
}
