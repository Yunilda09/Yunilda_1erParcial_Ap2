package com.ucne.proj_1erparcial_ap2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamoDao
import com.ucne.proj_1erparcial_ap2.data.local.dao.entity.OcupacionesDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity


@Database(
    entities = [
        PrestamosEntity::class, OcupacionesEntity::class
               ],
        version = 3
)

abstract class PrestamosDb: RoomDatabase(){
    abstract val prestamoDao: PrestamoDao
    abstract val ocupacionesDao: OcupacionesDao
}
