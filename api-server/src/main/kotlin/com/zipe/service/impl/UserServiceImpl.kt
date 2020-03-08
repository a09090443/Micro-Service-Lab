package com.zipe.service.impl

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.repository.ISysUserLogonLogRepository
import com.zipe.repository.ISysUserRepository
import com.zipe.service.IUserService
import com.zipe.utils.image.ImageUtils
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

    private val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    private var maxUserId: Int = 0

    @Autowired
    private lateinit var sysUserRepository: ISysUserRepository

    @Autowired
    private lateinit var sysUserLogonLogRepository: ISysUserLogonLogRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun findAllUsers(): MutableList<SysUserEntity> {
        val list: MutableIterable<SysUserEntity> = sysUserRepository.findAll()
        return list.toMutableList()
    }

    override fun findUserByLoginId(loginId: String): SysUserEntity {
        return sysUserRepository.findByLoginId(loginId)
    }

    override fun findUserByEmail(email: String): SysUserEntity {
        return sysUserRepository.findByEmail(email)
    }

    override fun findMaxLoginId(): SysUserEntity {
        return sysUserRepository.findTopByOrderByLoginIdDesc()
    }

    override fun saveUser(sysUserEntity: SysUserEntity) {
        val checkUser: SysUserEntity = sysUserRepository.findByLoginId(sysUserEntity.loginId)
        if (checkUser.userId.isNotBlank()) {
            log.error("This login_id has been registered!!")
//            throw Exception("This login_id has been registered!!")
            return
        }

        var newUserId: Int = 0

        val latestSysUser: SysUserEntity = sysUserRepository.findTopByOrderByLoginIdDesc()
        if (latestSysUser.userId.isNotBlank()) {
            maxUserId = latestSysUser.userId.toInt()
        }

        newUserId = maxUserId + 1
        var newLoginId: String = newUserId.toString().padStart(6, '0')

        sysUserEntity.password = passwordEncoder.encode(sysUserEntity.password)
        sysUserEntity.userId = newLoginId
        sysUserEntity.activated = true
        sysUserEntity.registerTime = Date()
        sysUserEntity.image = "$newLoginId." + ImageUtils.IMAGE_TYPE_JPG

        try {
            sysUserRepository.save(sysUserEntity)
        } catch (e: Exception) {

        }
    }

    override fun delUser(loginId: String) {
        sysUserRepository.deleteByLoginId(loginId)
    }

    override fun saveUserLogonRecord(sysUserLogonLogEntity: SysUserLogonLogEntity) {
        sysUserLogonLogRepository.save(sysUserLogonLogEntity)
    }

}
