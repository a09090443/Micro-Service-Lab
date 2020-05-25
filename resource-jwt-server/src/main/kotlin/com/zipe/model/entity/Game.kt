package com.zipe.model.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "game")
data class Game(

    @Id
    @Column(name = "id", nullable = false, length = 20, unique = true)
    var id: Long = 0,

    @Column(name = "name", length = 50)
    var name: String = "",

    @Column(name = "year", length = 4, nullable = true)
    var year: String = "",

    @Column(name = "price", length = 10)
    var price: Long = 0,

    @Column(name = "product_id", length = 20)
    var productId: Long = 0,

    @Column(name = "update_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var updateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_user", nullable = true, length = 256)
    var updateBy: String = "",

    @Column(name = "create_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var createTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_user", nullable = true, length = 256)
    var createBy: String = ""
)
