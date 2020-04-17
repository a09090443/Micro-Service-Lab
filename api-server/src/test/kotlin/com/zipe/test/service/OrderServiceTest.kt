package com.zipe.test.service

import com.zipe.repository.order.IOrderRepository
import com.zipe.service.IOrderService
import com.zipe.test.base.TestBase
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class OrderServiceTest : TestBase() {

    @Autowired
    lateinit var orderService: IOrderService

    @Test
    fun findOrderByUserIdTest() {
        val order = orderService.findOrderByUserId(1)
        println(order)
    }
}
