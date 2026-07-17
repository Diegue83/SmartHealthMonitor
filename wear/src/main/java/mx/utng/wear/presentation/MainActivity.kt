package mx.utng.wear.presentation

import WearDataSender
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.health.services.client.HealthServices
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import mx.utng.wear.HealthDataService
import mx.utng.wear.HeartRateManager
import mx.utng.wear.R
import mx.utng.wear.presentation.theme.SmartHealthWearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hsClient = HealthServices.getClient(applicationContext)
        val measureClient = hsClient.measureClient
        // Registrar el listener de Health Services
        lifecycleScope.launch {
            val capabilities =
                hsClient.measureClient.getCapabilitiesAsync().await()
            Log.d(
                "HealthServices",
                capabilities.supportedDataTypesMeasure.toString()
            )

            val capabilitiesMs =
                measureClient.getCapabilitiesAsync().await()

            Log.d(
                "HealthServices",
                capabilitiesMs.supportedDataTypesMeasure.toString()
            )

            HealthDataService.registrar(applicationContext)
            val bpm = 80

            HeartRateManager.updateHeartRate(bpm)

            WearDataSender(applicationContext)
                .enviarFC(bpm)

            Log.d("TEST", "BPM enviado: $bpm")
        }


        setContent {
            SmartHealthWearTheme {
                // TODO Ej.02: reemplazar con WearNavGraph
                WearDashboardScreen()
            }
        }

    }
}


@Composable
fun WearApp(greetingName: String) {
    val bpm = HeartRateManager.heartRate.collectAsState()

    SmartHealthWearTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = greetingName
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "♥ ${bpm.value} BPM"
            )
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}