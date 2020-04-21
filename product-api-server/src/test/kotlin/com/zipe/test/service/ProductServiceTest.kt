package com.zipe.test.service

import com.zipe.config.DynamicDataSourceContextHolder.getDataSourceName
import com.zipe.service.IProductService
import com.zipe.test.base.TestBase
import org.junit.Ignore
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest : TestBase() {

    @Autowired
    lateinit var productService: IProductService

//    @Ignore
    @Test
    fun findAllTest() {
//        setDataSourceName("secondaryDataSource")
//        println(getDataSourceName())
        val productList = productService.findAll()
        println(productList)
    }

}
