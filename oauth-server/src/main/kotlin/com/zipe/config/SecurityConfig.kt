package com.zipe.security.config

import com.zipe.security.service.LoginSuccessHandler
import com.zipe.security.service.LogoutSuccessHandler
import com.zipe.security.service.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun loginSuccessHandler(): LoginSuccessHandler {
        return LoginSuccessHandler()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var loginSuccessHandler: LoginSuccessHandler

    @Autowired
    private lateinit var logoutSuccessHandler: LogoutSuccessHandler

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .requestMatchers()
            .antMatchers("/**", "/login**", "/oauth/authorize", "/oauth/confirm_access")
            .and()
            .authorizeRequests()
            .antMatchers("/css/**", "/fonts/**", "/js/**", "/vendor/**", "/images/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll().successHandler(loginSuccessHandler)
            .and()
            .logout().logoutSuccessHandler(logoutSuccessHandler)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }
}
