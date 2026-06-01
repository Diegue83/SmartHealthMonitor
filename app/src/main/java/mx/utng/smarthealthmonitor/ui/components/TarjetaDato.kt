package mx.utng.smarthealthmonitor.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.utng.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme

@Composable
fun TarjetaDato(
    valor: String,                        // "78" o "4,250"
    unidad: String,                       // "bpm" o "pasos"
    label: String,                        // "Frecuencia cardíaca"
    colorValor: Color,                    // MaterialTheme.colorScheme.error
    modifier: Modifier = Modifier,         // ← siempre en Composables reutilizables
    icono: ImageVector?= null,
    colorIcono: Color? = null
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = valor,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = colorValor
                )
                Text(
                    text = unidad,
                    style = MaterialTheme.typography.titleSmall,
                    color = colorValor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(Modifier.width(5.dp))
                if (icono != null){
                    if (colorIcono != null) {
                        Icon(imageVector = icono, contentDescription = "icon", tint = colorIcono)
                    }else{
                        Icon(imageVector = icono, contentDescription = "icon")
                    }
                }
            }
        }
    }
}

// Preview con datos de prueba
@Preview(showBackground = true, name = "TarjetaDato - FC")
@Composable
private fun TarjetaDatoPreview() {
    SmartHealthMonitorTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TarjetaDato(
                valor = "69", unidad = "bpm",
                label = "Frecuencia cardíaca",
                colorValor = MaterialTheme.colorScheme.error,
                icono = Icons.Default.Favorite
            )
            TarjetaDato(
                valor = "4,250", unidad = "pasos",
                label = "Pasos del día",
                colorValor = MaterialTheme.colorScheme.primary
            )
        }
    }
}
