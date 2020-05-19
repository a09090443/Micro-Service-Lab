package ch.keepcalm.demo.oauth2.app

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
class WebSecurityConfiguration
