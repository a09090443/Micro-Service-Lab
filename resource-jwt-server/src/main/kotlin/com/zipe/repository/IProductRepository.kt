package com.zipe.repository

import com.zipe.model.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository : JpaRepository<Product, Long> {

}
