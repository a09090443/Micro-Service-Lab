package com.zipe.service

import com.zipe.entity.order.OrderListEntity

interface IOrderService {
    fun findAll(): List<OrderListEntity>

    fun findOrderByUserId(userId: Long): OrderListEntity

    fun insertOrder(order: OrderListEntity)

    fun updateOrder(order: OrderListEntity)
}
