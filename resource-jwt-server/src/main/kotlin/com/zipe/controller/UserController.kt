package com.zipe.controller

import com.zipe.base.controller.BaseController
import org.springframework.hateoas.MediaTypes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api", produces = [MediaTypes.HAL_JSON_VALUE])
class UserController : BaseController() {

    //    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping(value = ["/whoami"])
    fun whoami(principal: Principal?) = principal?.let { ResponseEntity.ok(it) }

}
