package com.zipe.service.impl

import com.zipe.entity.SysUserEntity
import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.repository.ISysUserLogonLogRepository
import com.zipe.repository.ISysUserRepository
import com.zipe.service.IUserService
import com.zipe.utils.image.ImageUtils
import com.zipe.utils.log.logger
import com.zipe.vo.SysUserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service("userService")
class UserServiceImpl : IUserService {

    val logger = logger()

    private var maxUserId = 0

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

    override fun saveUser(sysUserVO: SysUserVO) {
        val checkUser: SysUserEntity = sysUserRepository.findByLoginId(sysUserVO.loginId)
        val newUserEntity: SysUserEntity
        if (checkUser.userId.isNotBlank()) {
            logger.error("This login_id has been registered!!")
//            throw Exception("This login_id has been registered!!")
            return
        } else {
            newUserEntity = SysUserEntity()
        }

        var newUserId = 0

        val latestSysUser: SysUserEntity = sysUserRepository.findTopByOrderByLoginIdDesc()
        if (latestSysUser.userId.isNotBlank()) {
            maxUserId = latestSysUser.userId.toInt()
        }

        newUserId = maxUserId + 1
        val newLoginId: String = newUserId.toString().padStart(6, '0')

        newUserEntity.password = passwordEncoder.encode(sysUserVO.password)
        newUserEntity.userId = newLoginId
        newUserEntity.activated = true
        newUserEntity.registerTime = Date()
        newUserEntity.image = "$newLoginId." + ImageUtils.IMAGE_TYPE_JPG

        try {
            sysUserRepository.save(newUserEntity)
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
