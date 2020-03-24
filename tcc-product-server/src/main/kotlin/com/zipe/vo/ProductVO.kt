package com.zipe.vo

data class ProductVO(
    var productName: String = "",
    var orderId: Long = 0,
    var amount: Int = 0,
    var expectedReservingSeconds: Int = 0,
    var responseCode: String = "",
    var expiredSeconds: Long = 0
)
