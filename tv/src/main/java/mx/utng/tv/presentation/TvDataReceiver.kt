package mx.utng.tv.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import mx.utng.shared.data.SmartHealthRepository
import mx.utng.shared.data.models.LecturaFC

class TvDataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bpm = intent?.getIntExtra("bpm", 0) ?: 0
        Log.d("TvDataReceiver", "Recibido BPM por broadcast: $bpm")
        if (bpm > 0) {
            SmartHealthRepository.actualizarFC(bpm)
            val nuevaLectura = LecturaFC(valorBpm = bpm)
            SmartHealthRepository.agregarRegistroFC(nuevaLectura)
        }
    }
}
