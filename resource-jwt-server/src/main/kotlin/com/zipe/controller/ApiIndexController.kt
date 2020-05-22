package com.zipe.controller

import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
