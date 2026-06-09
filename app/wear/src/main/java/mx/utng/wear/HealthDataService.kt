package mx.utng.wear

import WearDataSender
import android.content.Context
import android.util.Log
import androidx.health.services.client.HealthServices
import androidx.health.services.client.PassiveListenerService
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.PassiveListenerConfig
import androidx.health.services.client.data.SampleDataPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

class HealthDataService : PassiveListenerService() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var wearDataSender: WearDataSender

    override fun onCreate() {
        super.onCreate()

        Log.d("HealthDataService", "Servicio creado")
        wearDataSender = WearDataSender(this)  // S6: MessageClient
    }

    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {

        Log.d("HealthDataService", "onNewDataPointsReceived llamado")

        val fcDataPoints = dataPoints.getData(DataType.HEART_RATE_BPM)

        Log.d(
            "HealthDataService",
            "Cantidad de datos FC recibidos: ${fcDataPoints.size}"
        )

        fcDataPoints.forEach { dataPoint ->

            Log.d(
                "HealthDataService",
                "DataPoint recibido: $dataPoint"
            )

            if (dataPoint is SampleDataPoint<Double>) {

                Log.d(
                    "HealthDataService",
                    "Entró al if SampleDataPoint<Double>"
                )

                val bpm = dataPoint.value.toInt()

                Log.d(
                    "HealthDataService",
                    "Heart Rate recibido: $bpm BPM"
                )

                HeartRateManager.updateHeartRate(bpm)

                scope.launch {
                    wearDataSender.enviarFC(bpm)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {
        suspend fun registrar(context: Context) {

            Log.d("HealthDataService", "Iniciando registro")

            val hsClient = HealthServices.getClient(context)
            val passiveClient = hsClient.passiveMonitoringClient

            val config = PassiveListenerConfig.builder()
                .setDataTypes(setOf(DataType.HEART_RATE_BPM))
                .setShouldUserActivityInfoBeRequested(true)
                .build()

            passiveClient.setPassiveListenerServiceAsync(
                HealthDataService::class.java,
                config
            ).await()

            Log.d("HealthDataService", "Registro completado")
        }
    }
}
