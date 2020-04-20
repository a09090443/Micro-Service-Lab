package com.zipe.repository.order

import com.zipe.entity.order.OrderListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IOrderListRepository : JpaRepository<OrderListEntity, Long> {

    fun findByUserId(userId: Long): OrderListEntity

    @Modifying
    @Query("update OrderListEntity ol set ol.price=:price where ol.id = :id")
    fun updateById(@Param(value = "id") id: Long, @Param(value = "price") price: Long)
}
