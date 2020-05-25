package com.zipe.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.zipe.model.entity.Product
import com.zipe.model.input.ProductInput
import com.zipe.service.impl.ProductServiceImpl
import com.zipe.util.UserInfoUtil
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
            this.createBy = UserInfoUtil().getUserInfo().name
            this.updateBy = UserInfoUtil().getUserInfo().name
        }
        productService.insertProduct(product)
        return "Success"
    }

}
