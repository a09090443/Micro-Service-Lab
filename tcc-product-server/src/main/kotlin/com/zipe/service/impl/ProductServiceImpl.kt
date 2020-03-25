package com.zipe.service.impl

import com.zipe.base.service.BaseService
import com.zipe.entity.TProductEntity
import com.zipe.entity.TProductTransactionEntity
import com.zipe.enum.ReservingState
import com.zipe.repository.IProductRepository
import com.zipe.repository.IProductTransacionRepository
import com.zipe.service.IProductService
import com.zipe.utils.log.logger
import com.zipe.vo.ProductVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

@Transactional
@Service("productService")
class ProductServiceImpl : IProductService, BaseService() {
    val logger = logger()

    @Autowired
    private lateinit var productRepository: IProductRepository

    @Autowired
    private lateinit var productTransactionRepository: IProductTransacionRepository

    override fun findAllProducts(): List<TProductEntity> = productRepository.findAll() as MutableList<TProductEntity>

    override fun findProductByName(name: String): TProductEntity? = productRepository.findByName(name)

    override fun reserving(productVO: ProductVO): ProductVO {

        val name = productVO.productName
        val product = productRepository.findByName(name)

        if (product == null) {
            logger.warn("Product is not exist")
        }

        val productTx = productTransactionRepository.findByOrderId(productVO.orderId)
        var productVOData = ProductVO()
        if (productTx.id > 0) {
            productVOData = recoverTransaction(productVO.orderId, productTx)
        } else {

        }

        return productVOData
    }

    private fun recoverTransaction(orderId: Long, accountTransaction: TProductTransactionEntity): ProductVO {
        val isTrying = accountTransaction.state == ReservingState.TRYING.status
        var productVO = ProductVO()
        if (accountTransaction.state == ReservingState.TRYING.status) {

            val expiredSeconds =
                Math.max(0, ChronoUnit.SECONDS.between(LocalDateTime.now(), accountTransaction.expireAt))
            if (expiredSeconds <= 0) {
                cancellableFindTransaction(orderId)
                val status = "CANCELLED"
            }
            productVO.expiredSeconds = expiredSeconds
            productVO.responseCode = "IDEMPOTENT_RESERVING"
        } else if (accountTransaction.state == ReservingState.INVALID.status) {
            productVO.responseCode = "UNKNOWN_RESERVING_STATE"
        } else {
            productVO.responseCode = "RESERVING_FINAL_STATE"
        }
        return ProductVO()
    }

    @Transactional
    fun newTransaction(orderId: Long, productId: Long, deductAmount: Long, reservingSeconds: Long): ProductVO {
        val productVO = ProductVO()
        if (productRepository.deductInventory(deductAmount, productId) > 0) {
            val accountTransaction = TProductTransactionEntity()
            accountTransaction.orderId = orderId
            accountTransaction.productId = productId
            accountTransaction.amount = deductAmount
            accountTransaction.state = ReservingState.TRYING.status
            val now = LocalDateTime.now()
            accountTransaction.createAt = now
            accountTransaction.updateAt = now
            accountTransaction.expireAt = now.plusSeconds(reservingSeconds)
            productTransactionRepository.save(accountTransaction)
            productVO.responseCode = "OK"

        } else {
            productVO.responseCode = "INSUFFICIENT_INVENTORY"
        }
        return productVO
    }

    fun cancellableFindTransaction(orderId: Long): TProductTransactionEntity {

        return TProductTransactionEntity()
    }
}
