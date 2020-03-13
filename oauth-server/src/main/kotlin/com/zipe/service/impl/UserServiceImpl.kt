package com.zipe.service.impl

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.repository.ISysUserLogonLogRepository
import com.zipe.repository.ISysUserRepository
import com.zipe.service.IUserService
import com.zipe.utils.image.ImageUtils
import com.zipe.utils.log.logger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service("userService")
class UserServiceImpl : IUserService {

    val logger = logger()

    @Autowired
    private lateinit var sysUserRepository: ISysUserRepository

    @Autowired
    private lateinit var sysUserLogonLogRepository: ISysUserLogonLogRepository

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
