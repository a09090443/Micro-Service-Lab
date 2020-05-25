package com.zipe.service.impl

import com.zipe.base.service.BaseService
import com.zipe.model.entity.Product
import com.zipe.repository.IProductRepository
import com.zipe.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("productService")
class ProductServiceImpl : IProductService, BaseService() {
    @Autowired
    private lateinit var productRepository: IProductRepository

    @Transactional(readOnly = true)
    override fun findProducts(page: Int, limit: Int): List<Product> {
        return productRepository.findAll(PageRequest.of(page, limit)).content.toList()
    }

    @Transactional
    override fun insertProduct(product: Product) {
        productRepository.save(product)
    }
}
