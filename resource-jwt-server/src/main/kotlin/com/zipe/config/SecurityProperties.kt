package com.zipe.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.core.io.Resource

//security.jwt:.public-key
@ConstructorBinding
@ConfigurationProperties(prefix = "security")
class SecurityProperties(var jwt: JwtProperties)
class JwtProperties(var publicKey: Resource)
