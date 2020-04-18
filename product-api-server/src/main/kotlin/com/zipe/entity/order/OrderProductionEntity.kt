package com.zipe.entity.order

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "order_production")
data class OrderProductionEntity(

    @Id
    @Column(name = "id", nullable = false, length = 20, unique = true)
    val id: Long = 0,

    @Column(name = "user_id", length = 20)
    val userId: Long = 0,

    @Column(name = "product_id", length = 20)
    val productId: String = "",

    @Column(name = "price", length = 10)
    val price: Long = 0,

    @Column(name = "update_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val updateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_user", nullable = true, length = 256)
    val updateBy: String = "",

    @Column(name = "create_time", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    val createTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_user", nullable = true, length = 256)
    val createBy: String = ""
)
