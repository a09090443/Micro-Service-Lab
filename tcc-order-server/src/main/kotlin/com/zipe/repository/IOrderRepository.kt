package com.zipe.repository

import com.zipe.entity.TOrderEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository("orderRepository")
interface IOrderRepository : CrudRepository<TOrderEntity, Long> {

    fun findByUserId(userId: Long): TOrderEntity?

}
