package com.zipe.repository.order

import com.zipe.entity.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderRepository : JpaRepository<OrderEntity, Long> {

    fun findByUserId(userId: Long): OrderEntity
}
