package com.zipe.service.impl

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.zipe.entity.TOrderEntity
import com.zipe.repository.IOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service("orderService")
class OrderServiceImpl : GraphQLQueryResolver {

    @Autowired
    private lateinit var orderRepository: IOrderRepository

    fun findAllOrders(): List<TOrderEntity> {
        return orderRepository.findAll() as MutableList<TOrderEntity>
    }

}
