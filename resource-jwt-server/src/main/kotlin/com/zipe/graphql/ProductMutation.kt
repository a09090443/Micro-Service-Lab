package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.zipe.model.product.entity.Product
import com.zipe.model.product.input.ProductInput
import com.zipe.service.impl.ProductServiceImpl
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductMutation : GraphQLMutationResolver {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    fun insertProduct(productInput: ProductInput): String {
        val product = Product().apply {
            BeanUtils.copyProperties(productInput, this)
        }
        productService.insertProduct(product)
        return "Success"
    }

}
