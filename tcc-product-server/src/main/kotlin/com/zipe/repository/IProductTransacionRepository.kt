package com.zipe.repository

import com.zipe.entity.TProductTransactionEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository("productTransactionRepository")
interface IProductTransacionRepository : CrudRepository<TProductTransactionEntity, Long> {

    fun findByOrderId(orderId: Long): TProductTransactionEntity

    @Modifying
    @Query("UPDATE TProductTransactionEntity pt SET pt.state=:state WHERE pt.id IN :id AND pt.state=:expected")
    fun compareAndSetState(
        @Param(value = "id") id: Long,
        @Param(value = "state") state: Int,
        @Param(value = "expected") expected: Int
    ): Int
}
