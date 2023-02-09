package com.ucne.proj_1erparcial_ap2.data.repository

import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamoDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrestamoRepository @Inject constructor(
    private val prestamoDao: PrestamoDao
    ){
    suspend fun insert(deudor: PrestamosEntity){
        return prestamoDao.insert(deudor)
    }

   // suspend fun fin(deudorId: Int) = prestamoDao.find(deudorId )

    fun getList(): Flow<List<PrestamosEntity>> = prestamoDao.getList()
}
