package com.zipe.service.impl

import com.zipe.base.service.BaseService
import com.zipe.config.DynamicDataSourceContextHolder.setDataSourceName
import com.zipe.entity.order.OrderEntity
import com.zipe.repository.order.IOrderRepository
import com.zipe.service.IOrderService
import com.zipe.utils.log.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service("orderService")
class OrderServiceImpl : IOrderService, BaseService() {
    val logger = logger()

    @Autowired
    private lateinit var orderRepository: IOrderRepository

    override fun findOrderByUserId(userId: Long): OrderEntity {
//        setDataSourceName("primaryDataSource")
        return orderRepository.findByUserId(userId);
    }

}
