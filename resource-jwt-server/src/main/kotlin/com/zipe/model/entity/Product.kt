package com.zipe.model.entity

import com.alibaba.fastjson.JSON
import com.zipe.model.output.GameOutput
import com.zipe.model.output.ProductOutput
import org.springframework.beans.BeanUtils
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(

    @Id
    @Column(name = "id", nullable = false, length = 20, unique = true)
    var id: Long = 0,

    @Column(name = "name", length = 50)
    var name: String = "",

    @Column(name = "price", length = 10)
    var price: Long = 0,

    @Column(name = "inventory", length = 20)
    var inventory: Long = 0,

    @Column(name = "update_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var updateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_user", nullable = true, length = 256)
    var updateBy: String = "",

    @Column(name = "create_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var createTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_user", nullable = true, length = 256)
    var createBy: String = "",

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id") // 指到關聯資料表Book的外鍵欄位名稱
    var games: List<Game> = listOf()
)

fun Product.asProductOutput() = ProductOutput().apply {
    BeanUtils.copyProperties(this@asProductOutput, this)
    this.games = JSON.parseArray(JSON.toJSONString(this@asProductOutput.games), GameOutput::class.java).toList()
}
