package com.zipe.test.repository

import com.zipe.entity.TProductEntity
import com.zipe.repository.IProductRepository
import com.zipe.test.base.TestBase
import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class TProductRepositoryTest : TestBase() {

    @Autowired
    lateinit var productRepository: IProductRepository

    @Ignore
    @Test
    fun getAllProducts() {

        val productList: MutableList<TProductEntity> = productRepository.findAll() as MutableList<TProductEntity>
        println(productList.size)
    }

    @Ignore
    @Test
    fun getProductById() {

        val product: Optional<TProductEntity> = productRepository.findById(1)
        println(product)
    }

    @Test
    fun getProductByName() {

        val product: TProductEntity? = productRepository.findByName("ps3")
        println(product)
    }
}
