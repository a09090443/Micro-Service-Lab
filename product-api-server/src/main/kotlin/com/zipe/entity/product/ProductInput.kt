package com.zipe.entity.product

import java.time.LocalDateTime

data class ProductInput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: LocalDateTime = LocalDateTime.now(),

    var updateBy: String = "",

    var createTime: LocalDateTime = LocalDateTime.now(),

    var createBy: String = ""
)
