package com.zipe.config

val dataSourceNames = mutableListOf<String>()

object DynamicDataSourceContextHolder {

    private val contextHolder = ThreadLocal<String>()

    fun setDataSourceName(name: String): Unit = contextHolder.set(name)

    fun getDataSourceName(): String = contextHolder.get()

    fun clearDataSourceName(): Unit = contextHolder.remove()

    fun containsDataSource(dataSourceName: String): Boolean = dataSourceNames.contains(dataSourceName)
}
