package com.zipe.service.impl

import com.zipe.annotation.DBRouting
import com.zipe.base.service.BaseService
import com.zipe.entity.order.OrderListEntity
import com.zipe.repository.order.IOrderListRepository
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
    private lateinit var orderListRepository: IOrderListRepository

    @DBRouting(DBRouting.SECONDARY_DATASOURCE)
    override fun findAll(): List<OrderListEntity> {
        return orderListRepository.findAll()
    }

    override fun findOrderByUserId(userId: Long): OrderListEntity {
        return orderListRepository.findByUserId(userId)
    }

    override fun insertOrder(order: OrderListEntity) {
        orderListRepository.save(order)
    }

    override fun updateOrder(order: OrderListEntity) {
        orderListRepository.updateById(order.id, order.price)
    }

}
