package com.zipe.controller

import com.zipe.entity2.CurrentStatusEntity
import com.zipe.repository2.ICurrentStatusRepository
import com.zipe.util.log.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    val logger = logger()

    @Autowired
    lateinit var currentStatusRepository: ICurrentStatusRepository

    @GetMapping(value = ["/test"])
    fun test(): MutableList<CurrentStatusEntity> {
        return currentStatusRepository.findAll() as MutableList<CurrentStatusEntity>
    }
}
