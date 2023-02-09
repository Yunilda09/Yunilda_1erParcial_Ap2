package com.ucne.proj_1erparcial_ap2.ui.prestamo

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {
    Column(
    Modifier.fillMaxSize()) {
        OcupacionBody(viewModel)
       //val uiState by viewModel.uiState.collectAsState() PrestamoListScreen(uiState.prestamoList)
    }
}

@Composable
fun OcupacionBody(
    viewModel: PrestamoViewModel ) {
    Column(
        modifier = Modifier.fillMaxWidth()) {

      OutlinedTextField(
          modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth(), value = viewModel.deudor,
          onValueChange = { viewModel.deudor = it },
          label = { Text("Deudor") }
      )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            label = { Text("Concepto") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            label = { Text("Monto") }
        )
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save,
                contentDescription = "Save") },
            onClick = {viewModel.insertar()}
    }
}

@Composable
private fun PrestamoScreen(prestamos: List<PrestamosEntity>){
   LazyColumn {
       items(prestamos){
               prestamos -> PrestamoRow(prestamos) }
       }
   }
@Composable
private fun PrestamoListScreen(prestamoList: List<PrestamosEntity>){
    LazyColumn {
        items(prestamoList) {
                prestamos -> PrestamoRow(prestamos)
         }
    }
}
@Composable
private fun PrestamoRow(prestamo:PrestamosEntity){
    Column(
        Modifier .fillMaxWidth()
            .padding(8.dp) )
    {
        Row
        (modifier = Modifier
            .fillMaxWidth())
        {
            Text(
                text = prestamo.DeudorId,
                style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(3f)
            )
            Text( String.format("%.2f", prestamo.Monto),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f) )
        } Divider(Modifier.fillMaxWidth())
    }
}



