package com.zipe.service

import com.zipe.model.product.entity.Product

interface IProductService {
    fun findProducts(page: Int, limit: Int): List<Product>

    fun insertProduct(product: Product)
}
