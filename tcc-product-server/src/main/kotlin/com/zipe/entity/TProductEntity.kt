package com.zipe.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "t_product")
data class TProductEntity(

    @Id
    @NotEmpty
    @Column(name = "id", nullable = false, length = 20, unique = true)
    val id: Long = 0,

    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    val name: String = "",

    @NotNull
    @Column(name = "inventory", nullable = false, length = 20)
    val inventory: Long = 0,

    @NotNull
    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    var updateAt: LocalDateTime = LocalDateTime.now()
)
