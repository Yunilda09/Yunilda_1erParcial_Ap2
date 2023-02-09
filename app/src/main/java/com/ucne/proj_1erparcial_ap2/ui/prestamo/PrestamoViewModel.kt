package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PrestamoUiState(
    val prestamosList: List<PrestamosEntity> = emptyList()
)
@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository )
    : ViewModel(){

    var deudor by mutableStateOf("")
    var concepto by mutableStateOf("")
    var monto by mutableStateOf("")

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
        }
    }
    fun insertar() {
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
        private fun Limpiar() {
            deudor = ""
            concepto = ""
            monto = ""
        }
    }

