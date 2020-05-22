package com.zipe.service

import com.zipe.model.product.entity.Product

interface IProductService {
    fun findAllProducts(): List<Product>

    fun insertProduct(product: Product)
}
