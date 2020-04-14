package com.zipe.test.repository

import com.zaxxer.hikari.HikariDataSource
import com.zipe.entity2.CurrentStatusEntity
import com.zipe.repository2.ICurrentStatusRepository
import com.zipe.test.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource

class CurrentStatusRepositoryTest : TestBase() {

    @Autowired
    lateinit var currentStatusRepository: ICurrentStatusRepository

    @Autowired
    lateinit var db2DataSource: DataSource

    @Test
    fun getAllUsers() {
        if(db2DataSource is HikariDataSource){
            println(db2DataSource)
        }else{
            println("GG")
        }
        val currentStatusList: MutableList<CurrentStatusEntity> = currentStatusRepository.findAll() as MutableList<CurrentStatusEntity>
        println(currentStatusList)
    }
}
