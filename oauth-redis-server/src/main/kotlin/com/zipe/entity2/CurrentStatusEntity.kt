package com.zipe.entity2

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "current_status")
data class CurrentStatusEntity(

    @Id
    @NotEmpty
    @Column(name = "status_id", nullable = false, length = 11, unique = true)
    val statusId: String = "",

    @NotNull
    @Column(name = "status_name", nullable = false, length = 5)
    val name: String = ""
)
