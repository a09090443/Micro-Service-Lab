package com.zipe.repository2

import com.zipe.entity2.CurrentStatusEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ICurrentStatusRepository : CrudRepository<CurrentStatusEntity, Long> {

    fun findTopByOrderByStatusIdDesc(): CurrentStatusEntity

}
