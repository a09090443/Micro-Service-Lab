package com.zipe.model.product.output

import java.time.LocalDateTime

data class ProductOutput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: String = "",

    var updateBy: String = "",

    var createTime: String = "",

    var createBy: String = ""
)
