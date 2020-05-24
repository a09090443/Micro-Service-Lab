package com.zipe.controller

import com.zipe.service.impl.ProductServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.MediaTypes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product", produces = [MediaTypes.HAL_JSON_VALUE])
class ProductController {

    @Autowired
    private lateinit var productService: ProductServiceImpl

    @GetMapping(value = ["/all"])
    fun getProducts(page: Int, limit: Int) = productService.findProducts(page, limit)
}
