package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import com.ucne.proj_1erparcial_ap2.data.repository.OcupacinesRepository
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PrestamoUiState(
    val prestamosList: List<PrestamosEntity> = emptyList(),
    val ocupcionesList: List<OcupacionesEntity> = emptyList()
)
@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository ,
    private val ocupacinesRepository: OcupacinesRepository
    ) : ViewModel(){

    var deudor by mutableStateOf("")
    var deudorError by mutableStateOf("")

    var concepto by mutableStateOf("")
    var conceptoError by mutableStateOf("")

    var monto by mutableStateOf("")
    var montoError by mutableStateOf("")

    var uiState = MutableStateFlow(PrestamoUiState())
        private set

    init {
        getLista()
    }
    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.getList().collect { lista ->
                uiState.update {
                    it.copy(prestamosList = lista)
                }
            }
            ocupacinesRepository.insert(OcupacionesEntity(nombre = "Ingeniero"))
            ocupacinesRepository.insert(OcupacionesEntity(nombre = "Doctor"))
            ocupacinesRepository.insert(OcupacionesEntity(nombre = "Abogado"))

            uiState.update {
                it.copy(ocupcionesList = ocupacinesRepository.getList())
            }
        }
    }

    fun onDeudorChanged(deudor: String) {
        this.deudor = deudor
        HayErrores()
    }
    fun onConceptoChanged(concepto: String){
        this.concepto = concepto
        HayErrores()
    }

    fun onMontoChanged(monto: String) {
        this.monto = monto
        HayErrores()
    }
    fun insertar() {
        if (HayErrores())
            return

            val prestamo = PrestamosEntity(
                deudor = deudor,
                concepto = concepto,
                monto = monto.toDoubleOrNull() ?: 0.0
            )

            viewModelScope.launch(Dispatchers.IO) {
                prestamoRepository.insert(prestamo)
                Limpiar()
            }
    }
        private fun HayErrores(): Boolean {
            var hayError = false
            deudorError = ""
            if (deudor.isBlank()) {
                deudorError = "Debe indicar el deudor"
                hayError = true
            }
            conceptoError = ""
            if (concepto.isBlank()){
                conceptoError = "Debes indicar el concepto"
                hayError = true
            }

            montoError = ""
            if ((monto.toDoubleOrNull() ?: 0.0) <= 0.0) {
                montoError = "Debe indicar un monto mayor que cero"
                hayError = true
            }
            return hayError
        }
        private fun Limpiar() {
            deudor = ""
            concepto = ""
            monto = ""
        }
    }

