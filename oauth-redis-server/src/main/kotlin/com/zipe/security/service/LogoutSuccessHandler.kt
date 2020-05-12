package com.zipe.security.service

import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.enums.LogonStatusEnum
import com.zipe.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service("logoutSuccessHandler")
class LogoutSuccessHandler : SimpleUrlLogoutSuccessHandler() {

    @Autowired
    private lateinit var userService: IUserService

    init {
        defaultTargetUrl = "/login"
        isAlwaysUseDefaultTargetUrl = true
    }

    override fun onLogoutSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?
    ) {
        if (authentication != null) {
            logger.debug("user:" + authentication.principal.toString() + "logout" + request.contextPath);
        } else {
            throw Exception("user data has problem!!")
        }

        val sysUserLogonLogEntity = SysUserLogonLogEntity()
        sysUserLogonLogEntity.loginId = authentication.principal.toString()
        sysUserLogonLogEntity.status = LogonStatusEnum.LOGOUT.name
        sysUserLogonLogEntity.loginTime = Date()
        userService.saveUserLogonRecord(sysUserLogonLogEntity)

        super.onLogoutSuccess(request, response, authentication);
    }

}
