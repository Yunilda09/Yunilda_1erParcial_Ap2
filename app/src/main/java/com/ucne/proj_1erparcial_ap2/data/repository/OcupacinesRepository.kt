package com.ucne.proj_1erparcial_ap2.data.repository

import com.ucne.proj_1erparcial_ap2.data.local.dao.entity.OcupacionesDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import javax.inject.Inject

class OcupacinesRepository @Inject constructor(
    private  val ocupacionesDao: OcupacionesDao,

    ) {
    suspend fun insert(ocupacion: OcupacionesEntity) {
        return ocupacionesDao.insert(ocupacion)
    }
    suspend fun getList(): List<OcupacionesEntity> = ocupacionesDao.getList()

}