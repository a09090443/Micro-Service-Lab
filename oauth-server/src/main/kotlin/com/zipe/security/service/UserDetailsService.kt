package com.zipe.security.service

import com.zipe.entity.SysAuthorityEntity
import com.zipe.entity.SysUserEntity
import com.zipe.exception.UserNotActivatedException
import com.zipe.service.IUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component("userDetailsService")
class UserDetailsService : org.springframework.security.core.userdetails.UserDetailsService {

    val log: Logger = LoggerFactory.getLogger(UserDetailsService::class.java)

    @Autowired
    private lateinit var userService: IUserService

    /**
     * Checks if user exists in DB and is activated.
     *
     * @param login
     *            username used on login
     * @return {@link UserDetails}
     */
    override fun loadUserByUsername(login: String?): UserDetails {
        log.debug("Authenticating {}", login)

        var lowercaseLogin: String = login?.toLowerCase() ?: ""

        var userFromDatabase = SysUserEntity()
        if (!lowercaseLogin.isBlank()) {
            if (lowercaseLogin.contains("@")) {
                userFromDatabase = userService.findUserByEmail(lowercaseLogin)
            } else {
                userFromDatabase = userService.findUserByLoginId(lowercaseLogin)
            }
        }

        if (lowercaseLogin.isBlank()) {
            throw UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database.")
        } else if (!userFromDatabase.activated) {
            throw UserNotActivatedException("User " + lowercaseLogin + " is not activated.")
        }

        var grantedAuthorities: MutableList<GrantedAuthority> = mutableListOf()
        for (sysAuthority: SysAuthorityEntity in userFromDatabase.authority) {

            var grantedAuthority: GrantedAuthority = SimpleGrantedAuthority(sysAuthority.name) as GrantedAuthority
            grantedAuthorities.add(grantedAuthority)
        }

        return User(userFromDatabase.loginId, userFromDatabase.password, grantedAuthorities)
    }

}
