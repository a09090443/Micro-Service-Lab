package com.zipe.entity

import javax.persistence.*

@Entity
@Table(name = "sys_menu")
data class SysMenuEntity(

    @Id
    @Column(name = "menu_id", nullable = false, length = 2, unique = true)
    var menuId: Int = 0,

    @Basic
    @Column(name = "menu_name", nullable = false, length = 100)
    var menuName: String = "",

    @Basic
    @Column(name = "path", length = 100)
    var path: String = "",

    @Basic
    @Column(name = "comment", length = 200)
    var comment: String = "",

    @Basic
    @Column(name = "order_id", nullable = false, length = 2)
    var orderId: Int = 0,

    @Basic
    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    var enabled: Boolean = false,

    @Basic
    @Column(name = "parent_id", nullable = false, length = 2)
    var parentId: Int = 0

)

