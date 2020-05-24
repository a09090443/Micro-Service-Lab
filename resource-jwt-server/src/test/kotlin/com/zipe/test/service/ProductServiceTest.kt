package com.zipe.test.service

import com.zipe.service.impl.ProductServiceImpl
import com.zipe.test.base.TestBase
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest : TestBase() {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    @Test
    fun findAllTest() {
//        setDataSourceName("secondaryDataSource")
//        println(getDataSourceName())
//        val productList = productService.findAllProducts()
//        println(productList)
    }
}
