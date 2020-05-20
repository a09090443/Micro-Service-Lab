package com.zipe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@SpringBootApplication
class ResourceJwtServerApplication

fun main(args: Array<String>) {
	runApplication<ResourceJwtServerApplication>(*args)
}

/**
 * An opinionated WebApplicationInitializer to run a SpringApplication from a traditional WAR deployment.
 * Binds Servlet, Filter and ServletContextInitializer beans from the application context to the servlet container.
 *
 * @link http://docs.spring.io/spring-boot/docs/current/api/index.html?org/springframework/boot/context/web/SpringBootServletInitializer.html
 */
fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder? {
	return application.sources(ResourceJwtServerApplication::class.java)
}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
			.authorizeRequests()
			.antMatchers("/test/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
	}
}
