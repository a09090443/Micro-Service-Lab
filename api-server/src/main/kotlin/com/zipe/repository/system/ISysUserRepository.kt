package com.zipe.repository.system

import com.zipe.entity.system.SysUserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ISysUserRepository : CrudRepository<SysUserEntity, Long> {

    //	@Query("FROM UserInfo U WHERE U.loginId = :loginId")
    fun findByLoginId(loginId: String): SysUserEntity

    fun findByEmail(email: String): SysUserEntity

    fun findTopByOrderByLoginIdDesc(): SysUserEntity

    fun deleteByLoginId(loginId: String)

//    fun findTopByOrderByLoginIdDesc(loginId: String): SysUserEntity

}
