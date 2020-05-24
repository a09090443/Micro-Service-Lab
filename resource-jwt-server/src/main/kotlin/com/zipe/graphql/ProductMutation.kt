package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.zipe.model.product.entity.Product
import com.zipe.model.product.input.ProductInput
import com.zipe.service.impl.ProductServiceImpl
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ProductMutation : GraphQLMutationResolver {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    fun insertProduct(productInput: ProductInput): String {
        val product = Product().apply {
            BeanUtils.copyProperties(productInput, this)
            val now = LocalDateTime.now()
            this.createTime = now
            this.updateTime = now
            this.createBy = "zipe"
            this.updateBy = "zipe"
        }
        productService.insertProduct(product)
        return "Success"
    }

}
