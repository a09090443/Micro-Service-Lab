package com.zipe.service

import com.zipe.entity.order.OrderEntity

interface IOrderService {
    fun findOrderByUserId(userId: Long): OrderEntity
}
