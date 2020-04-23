package com.zipe.entity.product

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

data class ProductInput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: LocalDateTime = LocalDateTime.now(),

    var updateBy: String = "",

    var createTime: LocalDateTime = LocalDateTime.now(),

    var createBy: String = ""
)
