package ch.keepcalm.demo.oauth2.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.hateoas.*
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.server.mvc.BasicLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.add
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@SpringBootApplication
//@EnableConfigurationProperties(SecurityProperties::class) // Needed only for IntelliJ
class ApplicationServiceApp() {}

fun main(args: Array<String>) {
    runApplication<ApplicationServiceApp>(*args)
}


@RestController
@RequestMapping("/", produces = [MediaTypes.HAL_JSON_VALUE])
class ApiIndexController : RepresentationModel<ApiIndexController>() {

    @GetMapping
    fun api(): RepresentationModel<ApiIndexController> {
        return RepresentationModel<ApiIndexController>().apply {
            add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiIndexController::class.java).api()).withSelfRel())
            add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController::class.java).whoami(null)!!).withRel("whoami"))
        }
    }
}

@RestController
@RequestMapping("/api", produces = [MediaTypes.HAL_JSON_VALUE])
class UserController {

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping(value = ["/whoami"])
    fun whoami(principal: Principal?) = principal?.let { ResponseEntity.ok(it) }

}
