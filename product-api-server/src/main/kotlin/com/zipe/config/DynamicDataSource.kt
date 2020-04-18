package com.zipe.config

import com.zipe.config.DynamicDataSourceContextHolder.getDataSourceName
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class DynamicDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        println(getDataSourceName())
        return getDataSourceName()
    }
}
