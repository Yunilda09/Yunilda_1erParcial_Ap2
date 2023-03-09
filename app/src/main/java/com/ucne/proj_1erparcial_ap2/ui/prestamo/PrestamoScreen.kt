package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.DocumentScanner
import androidx.compose.material.icons.rounded.FileUpload
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.twotone.Error
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen( viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {

        val uiState by viewModel.uiState.collectAsState()
        PrestamoBody(viewModel)

        PrestamoListScreen(uiState.prestamosList)
    }
        Text(
            text = "Registro de Prestamos",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamoBody(
    viewModel: PrestamoViewModel
){
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
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
            label = { Text("Deudor") },
            isError = viewModel.deudorError.isNotBlank(),
            trailingIcon = {
                if (viewModel.deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.deudorError.isNotBlank()) {
            Text(
                text = viewModel.deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }


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
            label = { Text("Concepto") },
            isError = viewModel.conceptoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.conceptoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.conceptoError.isNotBlank()) {
            Text(
                text = viewModel.conceptoError,
                color = MaterialTheme.colorScheme.error
            )
        }

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
            isError = viewModel.montoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.montoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (viewModel.montoError.isNotBlank()) {
            Text(
                text = viewModel.montoError,
                color = MaterialTheme.colorScheme.error
            )
        }


        Spacer(modifier = Modifier.padding(8.dp))

        ExtendedFloatingActionButton(
            modifier = Modifier
                //.size(120.dp,80.dp)
                .align(Alignment.End)
                .wrapContentSize(Alignment.Center),
            shape = CircleShape,
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    var deudor by remember {
        mutableStateOf("")
    }
    val deudorError by remember {
        mutableStateOf("")
    }
    var monto by remember {
        mutableStateOf("")
    }
    val montoError by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = deudor,
            onValueChange = { deudor = it },
            label = { Text("Deudor") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = montoError,
                color = MaterialTheme.colorScheme.error
            )
        }

    }
}



