package mx.utng.smarthealthmonitor.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mx.utng.shared.data.SmartHealthRepository
import mx.utng.shared.data.models.MockData

class DashboardViewModel : ViewModel() {

    // FC: viene del wearable real vía Repository.
    // Si es 0 (sin dato aún), usar valor simulado.
    val fc: StateFlow<Int> = SmartHealthRepository.fcFlow
        .map { if (it == 0) MockData.fcActual else it }
        .stateIn(
            scope          = viewModelScope,
            started        = SharingStarted.WhileSubscribed(5_000),
            initialValue   = MockData.fcActual
        )

    val pasos: StateFlow<Int> = SmartHealthRepository.pasosFlow
        .map { if (it == 0) MockData.pasosActual else it }
        .stateIn(
            scope        = viewModelScope,
            started      = SharingStarted.WhileSubscribed(5_000),
            initialValue = MockData.pasosActual
        )

    val spO2 = SmartHealthRepository.spo2
        .map { if (it == 0) MockData.sp02Actual else it }
        .stateIn(
            scope        = viewModelScope,
            started      = SharingStarted.WhileSubscribed(5_000),
            initialValue = MockData.sp02Actual
        )

    val historial = SmartHealthRepository.historial
        .map { historial ->
            if (historial.isEmpty()) {
                MockData.historialFC
            } else {
                historial
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MockData.historialFC
        )
}
