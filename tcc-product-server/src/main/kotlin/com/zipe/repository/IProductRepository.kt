package com.zipe.repository

import com.zipe.entity.TProductEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository("productRepository")
interface IProductRepository : CrudRepository<TProductEntity, Long> {

    fun findByName(name: String): TProductEntity?

    @Modifying
    @Query(
        "UPDATE TProductEntity pe SET pe.inventory = pe.inventory - :amount " +
                "where pe.id in :id and pe.inventory - :amount >= 0"
    )
    fun deductInventory(@Param(value = "amount") amount: Long, @Param(value = "id") id: Long): Long
}
