package com.zipe.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DBRouting(val datasourceName: String){
    companion object {
        const val PRODUCT_DATASOURCE = "productDataSource"
        const val PRIMARY_DATASOURCE = "primaryDataSource"
        const val SECONDARY_DATASOURCE = "secondaryDataSource"
        const val THIRD_DATASOURCE = "thirdDataSource"
        const val FOURTH_DATASOURCE = "fourthDataSource"
    }
}

