package com.zipe.test.service

import com.zipe.config.DynamicDataSourceContextHolder.getDataSourceName
import com.zipe.config.DynamicDataSourceContextHolder.setDataSourceName
import com.zipe.entity.order.OrderListEntity
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
//        setDataSourceName("primaryDataSource")
        println(getDataSourceName())
        val order = orderService.findOrderByUserId(1)
        println(order)
    }

    @Ignore
    @Test
    fun findAllTest() {
//        setDataSourceName("secondaryDataSource")
        println(getDataSourceName())
        val orderList = orderService.findAll()
        println(orderList)
    }

    @Ignore
    @Test
    fun insertOrderTest() {

        val order = OrderListEntity().apply {
            this.price = 666
            this.productId = "12"
        }
        setDataSourceName("primaryDataSource")
        orderService.insertOrder(order)

        setDataSourceName("secondaryDataSource")
        order.price = 123
        order.productId = "23"
        orderService.insertOrder(order)

    }

    @Test
    fun updateOrderTest() {

        val order = OrderListEntity().apply {
            this.id = 4
            this.price = 777
//            this.productId = "35"
        }
        setDataSourceName("primaryDataSource")
        orderService.updateOrder(order)

        setDataSourceName("secondaryDataSource")
        order.price = 542
//        order.productId = "23"
        orderService.insertOrder(order)

    }
}
