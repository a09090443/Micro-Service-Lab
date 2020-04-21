package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.zipe.entity.product.ProductEntity
import com.zipe.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductQuery : GraphQLQueryResolver {

    @Autowired
    lateinit var productService: IProductService

    fun findAllProducts(): List<ProductEntity> {
        return productService.findAll()
    }
}

