package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.DocumentScanner
import androidx.compose.material.icons.rounded.FileUpload
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.android.animation.SegmentType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen( viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(Modifier.fillMaxSize()) {

        OcupacionBody(viewModel)
        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamosList)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OcupacionBody(
    viewModel: PrestamoViewModel
){
    Column(modifier = Modifier.fillMaxWidth()) {

        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = "Registro de Prestamos",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))


        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Deudor") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Ballot,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Concepto") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AttachMoney,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Monto") },
        )
        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(120.dp,80.dp)
                .align(Alignment.End)
                .wrapContentSize(Alignment.Center),
            text = { Text("") },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Save,
                    contentDescription = "Save"
                )
            },
            onClick = { viewModel.insertar() }
        )
    }
}

@Composable
private fun PrestamoListScreen(prestamosList: List<PrestamosEntity>) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = "Lista de Prestamos",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
        items(prestamosList) { prestamos ->
            PrestamoRow(prestamos)
        }
    }
}

@Composable
private fun PrestamoRow(prestamo: PrestamosEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                Text(
                    text = prestamo.deudor,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                    // modifier = Modifier.weight(3f)
                )
                Text(
                    text = prestamo.concepto,
                    style = MaterialTheme.typography.titleMedium,
                    //modifier = Modifier.weight(3f)
                )
            }
            Text(
                String.format("%.2f", prestamo.monto),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}



