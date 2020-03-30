package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "t_order")
data class TOrderEntity(

    @Id
    @NotEmpty
    @Column(name = "id", nullable = false, length = 20, unique = true)
    val id: Long = 0,

    @NotNull
    @Column(name = "user_id", nullable = false, length = 20)
    val userId: Long = 0,

    @NotNull
    @Column(name = "product_id", nullable = false, length = 20)
    val productId: Long = 0,

    @NotNull
    @Column(name = "price", nullable = false, length = 10)
    val price: Int = 0,

    @NotNull
    @Column(name = "quantity", nullable = false, length = 10)
    val quantity: Int = 0,

    @NotNull
    @Column(name = "state", nullable = false, length = 3)
    val state: Int = 0,

    @NotNull
    @Column(name = "guid", nullable = false, length = 20)
    val guid: Int = 0,

    @NotNull
    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var updateAt: LocalDateTime = LocalDateTime.now()
)
