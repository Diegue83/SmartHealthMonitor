package mx.utng.tv.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvCatalogScreen(
    viewModel: TvViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Cargando datos de salud...",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    } else {
        TvLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Título Principal
            item {
                Text(
                    text = "Smart Health Monitor TV",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                )
            }

            // Fila 1: Últimas 3 lecturas + FC Actual
            item {
                Column {
                    Text(
                        text = "Resumen Reciente - Actual: ${uiState.fcActual} BPM",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.LightGray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TvLazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(uiState.lecturas.take(3)) { lectura ->
                            FcCardItem(lectura = lectura, onClick = { /* Acción */ })
                        }
                    }
                }
            }

            // Fila 2: Historial Completo
            item {
                Column {
                    Text(
                        text = "Historial Completo",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.LightGray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TvLazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(uiState.lecturas) { lectura ->
                            FcCardItem(lectura = lectura, onClick = { /* Acción */ })
                        }
                    }
                }
            }
        }
    }
}
