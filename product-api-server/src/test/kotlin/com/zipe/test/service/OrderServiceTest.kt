package com.zipe.test.service

import com.zipe.config.DynamicDataSourceContextHolder.getDataSourceName
import com.zipe.config.DynamicDataSourceContextHolder.setDataSourceName
import com.zipe.service.IOrderService
import com.zipe.test.base.TestBase
import org.junit.Ignore
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class OrderServiceTest : TestBase() {

    @Autowired
    lateinit var orderService: IOrderService

    @Ignore
    @Test
    fun findOrderByUserIdTest() {
        setDataSourceName("primaryDataSource")
        println(getDataSourceName())
        val order = orderService.findOrderByUserId(1)
        println(order)
    }

    @Test
    fun findAllTest() {
        setDataSourceName("secondaryDataSource")
        println(getDataSourceName())
        val orderList = orderService.findAll()
        println(orderList)
    }
}
