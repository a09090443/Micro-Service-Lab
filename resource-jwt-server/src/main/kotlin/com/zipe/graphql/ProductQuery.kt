package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.zipe.model.entity.asProductOutput
import com.zipe.model.output.ProductOutput
import com.zipe.service.impl.ProductServiceImpl
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductQuery : GraphQLQueryResolver {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    fun findProducts(page: Int, limit: Int): List<ProductOutput> {
        return productService.findProducts(page, limit).map { it.asProductOutput() }.toList()
    }

}

