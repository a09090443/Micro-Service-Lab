package com.zipe.entity.product

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product")
data class ProductEntity(

    @Id
    @Column(name = "id", nullable = false, length = 20, unique = true)
    val id: Long = 0,

    @Column(name = "name", length = 20)
    val name: String = "",

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
