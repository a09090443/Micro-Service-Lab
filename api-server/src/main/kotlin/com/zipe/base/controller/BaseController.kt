package com.zipe.base.controller

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


abstract class BaseController {
    protected lateinit var request: HttpServletRequest
    protected lateinit var response: HttpServletResponse

    protected lateinit var currentLocale: Locale

    @ModelAttribute
    open fun myModel(request: HttpServletRequest, response: HttpServletResponse, model: Model) {
        this.request = request
        this.response = response
        currentLocale = LocaleContextHolder.getLocale()
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    open fun getPrincipal(): String {
        var userName: String = ""
        val principal = SecurityContextHolder.getContext().authentication.principal
        userName = if (principal is UserDetails) {
            (principal as UserDetails).username
        } else {
            principal.toString()
        }
        return userName
    }
}
