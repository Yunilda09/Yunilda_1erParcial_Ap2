package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert()
}