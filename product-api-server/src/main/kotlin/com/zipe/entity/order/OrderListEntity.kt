package com.zipe.entity.order

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "order_list")
data class OrderListEntity(

    @Id
    @Column(name = "id", nullable = false, length = 20, unique = true)
    var id: Long = 0,

    @Column(name = "user_id", length = 20)
    var userId: Long = 0,

    @Column(name = "product_id", length = 20)
    var productId: String = "",

    @Column(name = "price", length = 10)
    var price: Long = 0,

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
