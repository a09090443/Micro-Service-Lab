package com.zipe.security.service

import com.zipe.entity.SysUserEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class LoginSuccessHandler : SavedRequestAwareAuthenticationSuccessHandler() {
    private val log: Logger = LoggerFactory.getLogger(LoginSuccessHandler::class.java)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {

        val userDetails: SysUserEntity = authentication.getPrincipal() as SysUserEntity

        log.info("Login user:" + userDetails.lastName + "login" + request.getContextPath());
        log.info("IP:" + getIpAddress(request));
    }

    fun getIpAddress(request: HttpServletRequest): String {
        var ip = request.getHeader("x-forwarded-for")
        if ("unknown".equals("unknown", ignoreCase = false)){
            if (ip.isEmpty()) {
                ip = request.remoteAddr
            }
        }

        return ip
    }
}
