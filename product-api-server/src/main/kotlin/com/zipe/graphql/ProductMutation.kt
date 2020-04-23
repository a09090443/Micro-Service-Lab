package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.zipe.entity.product.ProductEntity
import com.zipe.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductMutation : GraphQLMutationResolver {

    @Autowired
    lateinit var productService: IProductService

    fun insertProduct(product: ProductEntity) = productService.insertProduct(product)

}
