package com.zipe.model.input

import com.zipe.model.entity.Game
import java.time.LocalDateTime
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

data class ProductInput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: LocalDateTime = LocalDateTime.now(),

    var updateBy: String = "",

    var createTime: LocalDateTime = LocalDateTime.now(),

    var createBy: String = ""
)
