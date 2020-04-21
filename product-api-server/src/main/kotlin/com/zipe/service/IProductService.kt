package com.zipe.service

import com.zipe.entity.product.ProductEntity

interface IProductService {

    fun findAll(): List<ProductEntity>

}
