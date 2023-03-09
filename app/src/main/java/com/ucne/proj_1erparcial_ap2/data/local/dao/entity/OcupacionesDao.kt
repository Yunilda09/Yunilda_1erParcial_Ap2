package com.ucne.proj_1erparcial_ap2.data.local.dao.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity

@Dao
interface OcupacionesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamoEntity: OcupacionesEntity)

    @Query(
        """
        SELECT * 
        FROM Ocupaciones
    """
    )
    suspend fun getList(): List<OcupacionesEntity>
}