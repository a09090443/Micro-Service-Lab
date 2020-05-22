package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.zipe.model.product.entity.Product
import com.zipe.service.impl.ProductServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductQuery : GraphQLQueryResolver {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    fun findAllProducts(): List<Product> {
        return productService.findAllProducts()
    }
}

