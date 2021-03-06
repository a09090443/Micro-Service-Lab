package com.zipe.repository.product

import com.zipe.model.product.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository : JpaRepository<ProductEntity, Long> {

}
