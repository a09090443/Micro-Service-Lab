package com.zipe.service.impl

import com.zipe.annotation.DBRouting
import com.zipe.base.service.BaseService
import com.zipe.entity.order.OrderProductionEntity
import com.zipe.repository.order.IOrderProductionRepository
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
    private lateinit var orderProductionRepository: IOrderProductionRepository

    override fun findOrderByUserId(userId: Long): OrderProductionEntity {
        return orderProductionRepository.findByUserId(userId)
    }

    @DBRouting(DBRouting.SECONDARY_DATASOURCE)
    override fun findAll(): List<OrderProductionEntity> {
        return orderProductionRepository.findAll()
    }

}
