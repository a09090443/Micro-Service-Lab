package com.zipe.service

import com.zipe.model.entity.Product

interface IProductService {
    fun findProducts(page: Int, limit: Int): List<Product>

    fun insertProduct(product: Product)
}
