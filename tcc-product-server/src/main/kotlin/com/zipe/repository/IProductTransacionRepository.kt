package com.zipe.repository

import com.zipe.entity.TProductTransactionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository("productTransactionRepository")
interface IProductTransacionRepository : CrudRepository<TProductTransactionEntity, Long> {

    fun findByOrderId(orderId: Long): TProductTransactionEntity
}
