package com.zipe.test.service

import com.zipe.annotation.DBRouting
import com.zipe.config.DynamicDataSourceContextHolder.getDataSourceName
import com.zipe.config.DynamicDataSourceContextHolder.setDataSourceName
import com.zipe.entity.product.ProductEntity
import com.zipe.service.IProductService
import com.zipe.test.base.TestBase
import org.junit.Ignore
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest : TestBase() {

    @Autowired
    lateinit var productService: IProductService

    @Ignore
    @Test
    fun findAllTest() {
//        setDataSourceName("secondaryDataSource")
//        println(getDataSourceName())
        val productList = productService.findAll()
        println(productList)
    }

    @Test
    fun insertProductTest() {
        val productEntity = ProductEntity(name = "ps2", price = 300, inventory = 30)
        setDataSourceName(DBRouting.PRIMARY_DATASOURCE)
        println(getDataSourceName())
        productService.insertProduct(productEntity)

        setDataSourceName(DBRouting.SECONDARY_DATASOURCE)
        println(getDataSourceName())
        productService.insertProduct(productEntity)
    }
}
