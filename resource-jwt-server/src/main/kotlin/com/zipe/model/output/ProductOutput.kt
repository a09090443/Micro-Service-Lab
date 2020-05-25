package com.zipe.model.output

data class ProductOutput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: String = "",

    var updateBy: String = "",

    var createTime: String = "",

    var createBy: String = "",

    var games: Set<GameOutput> = setOf()
)
