package com.zipe.service.impl

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.repository.ISysUserLogonLogRepository
import com.zipe.repository.ISysUserRepository
import com.zipe.service.IUserService
import com.zipe.util.log.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service("userService")
class UserServiceImpl : IUserService {

    val logger = logger()

    @Autowired
    private lateinit var sysUserRepository: ISysUserRepository

    @Autowired
    private lateinit var sysUserLogonLogRepository: ISysUserLogonLogRepository

    @Cacheable(cacheNames = ["userCache"])
    override fun findUserByLoginId(loginId: String): SysUserEntity {
        return sysUserRepository.findByLoginId(loginId)
    }

    override fun findUserByEmail(email: String): SysUserEntity {
        return sysUserRepository.findByEmail(email)
    }

    override fun saveUserLogonRecord(sysUserLogonLogEntity: SysUserLogonLogEntity) {
        sysUserLogonLogRepository.save(sysUserLogonLogEntity)
    }

}
