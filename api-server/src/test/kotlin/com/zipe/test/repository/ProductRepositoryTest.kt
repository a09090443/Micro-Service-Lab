package com.zipe.test.repository

import com.zipe.repository.product.IProductRepository
import com.zipe.test.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductRepositoryTest : TestBase() {

    @Autowired
    lateinit var productRepository: IProductRepository

    @Test
    fun getAllProduct() {
        val productList = productRepository.findAll()
        println(productList.size)
    }
}
