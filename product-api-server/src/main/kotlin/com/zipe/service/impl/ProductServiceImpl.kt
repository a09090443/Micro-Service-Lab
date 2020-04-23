package com.zipe.service.impl

import com.zipe.annotation.DBRouting
import com.zipe.base.service.BaseService
import com.zipe.entity.product.ProductEntity
import com.zipe.repository.price.IProductRepository
import com.zipe.service.IProductService
import com.zipe.utils.log.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service("productService")
class ProductServiceImpl : IProductService, BaseService() {
    val logger = logger()

    @Autowired
    private lateinit var productRepository: IProductRepository

    @DBRouting(DBRouting.PRIMARY_DATASOURCE)
    override fun findAll(): List<ProductEntity> {
        return productRepository.findAll()
    }

    override fun insertProduct(productEntity: ProductEntity) {
        productRepository.save(productEntity)
    }

}
