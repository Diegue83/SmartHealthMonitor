package mx.utng.wear

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WearDataSender(healthDataService: HealthDataService) : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    fun enviarFC(bpm: Int) {
        TODO("Not yet implemented")
    }
}