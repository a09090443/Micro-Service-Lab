package com.zipe.repository.order

import com.zipe.entity.order.OrderProductionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderProductionRepository : JpaRepository<OrderProductionEntity, Long> {

    fun findByUserId(userId: Long): OrderProductionEntity
}
