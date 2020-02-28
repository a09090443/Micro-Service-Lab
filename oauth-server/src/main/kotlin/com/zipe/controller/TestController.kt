package com.zipe.controller

import com.zipe.entity2.CurrentStatusEntity
import com.zipe.repository2.ICurrentStatusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @Autowired
    lateinit var currentStatusRepository: ICurrentStatusRepository

    @GetMapping(value = ["/test"])
    fun test(): MutableList<CurrentStatusEntity> {
        val currentStatusList: MutableList<CurrentStatusEntity> =
            currentStatusRepository.findAll() as MutableList<CurrentStatusEntity>
        return currentStatusList
    }
}
