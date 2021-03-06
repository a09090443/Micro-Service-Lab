package com.zipe.model.output

data class ProductOutput(

    var name: String = "",

    var price: Long = 0,

    var inventory: Long = 0,

    var updateTime: String = "",

    var updateBy: String = "",

    var createTime: String = "",

    var createBy: String = "",

    var games: List<GameOutput> = listOf()
) {
    fun games(name: String?): List<GameOutput> = games.filter { name.isNullOrBlank() || name == it.name }.toList()
}
