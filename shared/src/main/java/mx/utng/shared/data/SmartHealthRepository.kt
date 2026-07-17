package mx.utng.shared.data

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.utng.shared.data.db.LecturaFCDao
import mx.utng.shared.data.db.SmartHealthDB
import mx.utng.shared.data.models.LecturaFC

/**
 * Repositorio singleton que centraliza los datos de salud.
 * El WearListenerService escribe aquí.
 * El ViewModel lee de aquí.
 */
object SmartHealthRepository {

    // FC actual del wearable (bpm)
    private val _fcFlow = MutableStateFlow(0)
    val fcFlow: StateFlow<Int> = _fcFlow.asStateFlow()

    // Pasos del día actual
    private val _pasosFlow = MutableStateFlow(0)
    val pasosFlow: StateFlow<Int> = _pasosFlow.asStateFlow()

    private val _spo2 = MutableStateFlow(0)
    val spo2: StateFlow<Int> = _spo2

    private val _historial = MutableStateFlow<List<LecturaFC>>(emptyList())
    val historial: StateFlow<List<LecturaFC>> = _historial.asStateFlow()

    fun actualizarFC(bpm: Int) {
        _fcFlow.value = bpm
    }

    fun actualizarPasos(pasos: Int) {
        _pasosFlow.value = pasos
    }

    fun agregarSpO2Flow(valor: Int) {
        _spo2.value = valor
    }

    fun agregarRegistroFC(lectura: LecturaFC) {
        _historial.value = listOf(lectura) + _historial.value
    }

    private var dao: LecturaFCDao? = null

    fun init(context: Context) {
        dao = SmartHealthDB.getDatabase(context).lecturaDao()
    }
}
