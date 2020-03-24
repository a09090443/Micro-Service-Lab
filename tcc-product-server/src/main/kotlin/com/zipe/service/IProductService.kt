package com.zipe.service

import com.zipe.entity.TProductEntity
import com.zipe.vo.ProductVO

interface IProductService {

    fun findAllProducts(): List<TProductEntity>

    fun findProductByName(name: String): TProductEntity?

    fun reserving(productVO: ProductVO): ProductVO
}
