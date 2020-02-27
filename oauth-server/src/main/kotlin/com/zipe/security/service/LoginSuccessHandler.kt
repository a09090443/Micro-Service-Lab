package com.zipe.security.service

import com.zipe.entity.SysUserLogonLogEntity
import com.zipe.enums.LogonStatusEnum
import com.zipe.service.IUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service("loginSuccessHandler")
class LoginSuccessHandler : SavedRequestAwareAuthenticationSuccessHandler() {
    private val log: Logger = LoggerFactory.getLogger(LoginSuccessHandler::class.java)

    @Autowired
    private lateinit var userService: IUserService

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val principal: Any = authentication.principal
        val userId = (principal as UserDetails).username
        val sysUserLogonLogEntity = SysUserLogonLogEntity()
        sysUserLogonLogEntity.loginId = userId
        sysUserLogonLogEntity.status = LogonStatusEnum.LOGIN.name
        sysUserLogonLogEntity.loginTime = Date()
        userService.saveUserLogonRecord(sysUserLogonLogEntity)
//            val userDetails: SysUserEntity = authentication.getPrincipal() as SysUserEntity
//
//            log.info("Login user:" + userDetails.lastName + "login" + request.getContextPath());

        log.info("IP:" + getIpAddress(request));


        super.onAuthenticationSuccess(request, response, authentication)
    }

    fun getIpAddress(request: HttpServletRequest): String {
        var ip = request.getHeader("x-forwarded-for")
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getRemoteAddr()
        }
        return ip
    }
}
