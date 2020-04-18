package com.zipe.service

import com.zipe.entity.order.OrderProductionEntity

interface IOrderService {
    fun findOrderByUserId(userId: Long): OrderProductionEntity
    fun findAll(): List<OrderProductionEntity>
}
