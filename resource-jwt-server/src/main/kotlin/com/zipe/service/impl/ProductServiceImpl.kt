package com.zipe.service.impl

import com.zipe.base.service.BaseService
import com.zipe.model.product.entity.Product
import com.zipe.repository.product.IProductRepository
import com.zipe.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("productService")
class ProductServiceImpl : IProductService, BaseService() {
    @Autowired
    private lateinit var productRepository: IProductRepository

    @Transactional(readOnly = true)
    override fun findAllProducts(): List<Product> = productRepository.findAll()

    @Transactional
    override fun insertProduct(product: Product) {
        productRepository.save(product)
    }
}
