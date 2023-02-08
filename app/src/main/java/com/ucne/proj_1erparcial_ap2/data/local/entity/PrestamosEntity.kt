package com.ucne.proj_1erparcial_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName= "Prestamos")
    data class PrestamosEntity(
    @PrimaryKey(autoGenerate = false)

    val DeudorId: Int? = null,
    val Cocepto: String,
    val Monto: Double

    )