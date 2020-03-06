package com.zipe.repository

import com.zipe.entity.SysUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ISysUserRepository : CrudRepository<SysUserEntity, Long> {

    //	@Query("FROM UserInfo U WHERE U.loginId = :loginId")
    fun findByLoginId(loginId: String): SysUserEntity

    fun findByEmail(email: String): SysUserEntity

    fun findTopByOrderByLoginIdDesc(): SysUserEntity

//    fun findTopByOrderByLoginIdDesc(loginId: String): SysUserEntity

}
