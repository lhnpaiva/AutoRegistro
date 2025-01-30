package com.lhnpaiva.autoregistro.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lhnpaiva.autoregistro.R
import com.lhnpaiva.autoregistro.domain.model.Gasto
import com.lhnpaiva.autoregistro.domain.model.Veiculo
import com.lhnpaiva.autoregistro.presentation.components.MainToolbar
import com.lhnpaiva.autoregistro.presentation.theme.GrayColor
import com.lhnpaiva.autoregistro.presentation.theme.PrimaryColor
import com.lhnpaiva.autoregistro.presentation.theme.WhiteColor

val sampleVeiculos = listOf(
    Veiculo(
        id = 1,
        nome = "Carro A",
        modelo = "Modelo X",
        placa = "ABC-1234",
        quilometragem = 50000,
        gastos = listOf(
            Gasto(id = 1, descricao = "Troca de Óleo", kmPrevisto = 51000, urgente = true),
            Gasto(id = 2, descricao = "Revisão de Freios", kmPrevisto = 52000, urgente = false)
        )
    ),
    Veiculo(
        id = 2,
        nome = "Carro B",
        modelo = "Modelo Y",
        placa = "DEF-5678",
        quilometragem = 75000,
        gastos = listOf(
            Gasto(id = 3, descricao = "Alinhamento", kmPrevisto = 76000, urgente = false),
            Gasto(id = 4, descricao = "Troca de Pneus", kmPrevisto = 80000, urgente = true)
        )
    )
)

@Composable
internal fun HomeScreen(
    onBackClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    var veiculoSelecionado by remember { mutableStateOf(sampleVeiculos.first()) }
    var veiculosList by remember { mutableStateOf(sampleVeiculos) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            MainToolbar(
                title = stringResource(id = R.string.home_title),
                onBackClick = onBackClick,
                onHelpClick = onHelpClick
            )
        },
        containerColor = WhiteColor
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(innerPadding)
                    .padding(16.dp)
                    .padding(bottom = 120.dp)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(veiculosList) { veiculo ->
                        VeiculoCard(
                            veiculo = veiculo,
                            isSelected = veiculo.id == veiculoSelecionado.id,
                            onClick = { veiculoSelecionado = veiculo }
                        )
                    }
                    item {
                        AddVeiculoCard(
                            onClick = {
                                // Implementar a lógica para adicionar um novo veículo
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                SectionTitle(
                    title = stringResource(
                        id = R.string.home_maintenance_section_title,
                        veiculoSelecionado
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (veiculoSelecionado.gastos.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.home_maintenance_without_items),
                            style = MaterialTheme.typography.bodyMedium,
                            color = PrimaryColor
                        )
                    } else {
                        veiculoSelecionado.gastos.forEach { manutencao ->
                            ManutencaoItem(manutencao)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(WhiteColor)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                QuickActionsRow()
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = PrimaryColor
    )
}

@Composable
fun VeiculoCard(
    veiculo: Veiculo,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable { onClick() }
            .shadow(
                elevation = if (isSelected) 8.dp else 4.dp,
                shape = MaterialTheme.shapes.medium,
                clip = true
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) PrimaryColor else WhiteColor.copy(alpha = 0.75f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.DirectionsCar,
                contentDescription = stringResource(id = R.string.home_car_card),
                tint = if (isSelected) WhiteColor else PrimaryColor,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = veiculo.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) WhiteColor else PrimaryColor
            )

            Text(
                text = veiculo.modelo,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) WhiteColor.copy(alpha = 0.9f) else PrimaryColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.home_car_card_plate, veiculo.placa),
                style = MaterialTheme.typography.bodySmall,
                color = if (isSelected) WhiteColor.copy(alpha = 0.9f) else PrimaryColor
            )

            Text(
                text = stringResource(id = R.string.home_car_card_mileage, veiculo.quilometragem),
                style = MaterialTheme.typography.bodySmall,
                color = if (isSelected) WhiteColor.copy(alpha = 0.9f) else PrimaryColor
            )
        }
    }
}

@Composable
fun AddVeiculoCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .height(193.dp)
            .clickable { onClick() }
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
                clip = true
            ),
        colors = CardDefaults.cardColors(
            containerColor = WhiteColor.copy(alpha = 0.75f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.home_add_new_car_icon),
                tint = PrimaryColor,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.home_add_new_car),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ManutencaoItem(gasto: Gasto) {
    val isUrgente = gasto.urgente

    val containerColor = if (isUrgente) PrimaryColor else WhiteColor
    val iconColor = if (isUrgente) WhiteColor else PrimaryColor
    val textColor = if (isUrgente) WhiteColor else PrimaryColor
    val subTextColor = if (isUrgente) WhiteColor.copy(alpha = 0.8f) else GrayColor

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isUrgente) Icons.Default.Warning else Icons.Default.Info,
                contentDescription = stringResource(id = R.string.home_alert_content_description),
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = gasto.descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.home_maintenance_planned, gasto.kmPrevisto),
                    style = MaterialTheme.typography.bodySmall,
                    color = subTextColor
                )
            }
        }
    }
}

@Composable
fun QuickActionsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuickActionButton(
            icon = Icons.Default.LocalGasStation,
            label = stringResource(id = R.string.home_fuel_card),
            onClick = { /* Ação de Abastecimento */ }
        )
        QuickActionButton(
            icon = Icons.Default.Build,
            label = stringResource(id = R.string.home_maintenance_card),
            onClick = { /* Ação de Manutenção */ }
        )
        QuickActionButton(
            icon = Icons.Default.History,
            label = stringResource(id = R.string.home_expense_card),
            onClick = { /* Ação de Despesa */ }
        )
    }
}

@Composable
fun QuickActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = PrimaryColor,
            contentColor = WhiteColor,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = PrimaryColor
        )
    }
}
