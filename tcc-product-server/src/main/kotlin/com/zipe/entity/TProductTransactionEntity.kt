package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "t_product_transaction")
data class TProductTransactionEntity(

    @Id
    @NotEmpty
    @Column(name = "id", nullable = false, length = 20, unique = true)
    var id: Long = 0,

    @NotNull
    @Column(name = "product_id", nullable = false, length = 20)
    var productId: Long = 0,

    @NotNull
    @Column(name = "order_id", nullable = false, length = 20)
    var orderId: Long = 0,

    @NotNull
    @Column(name = "amount", nullable = false, length = 20)
    var amount: Long = 0,

    @NotNull
    @Column(name = "state", nullable = false, length = 4)
    var state: Int = 0,

    @NotNull
    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var updateAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "expire_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var expireAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "done_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var doneAt: LocalDateTime = LocalDateTime.now()
)
