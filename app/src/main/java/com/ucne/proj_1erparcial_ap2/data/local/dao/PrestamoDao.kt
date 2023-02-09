package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.*
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamosEntity: PrestamosEntity)

    @Query(""" SELETC * FROM Prestamos WHERE DeudorId =: deudorId LIMIT 1 """)
    suspend fun find(deudorId: Int): PrestamosEntity?

    @Query("""SELECT * FROM Prestamos ORDER BY deudorId desc """)
    fun getList(): Flow<List<PrestamosEntity>>


}