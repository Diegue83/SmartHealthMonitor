package mx.utng.wear

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object HeartRateManager {
    private val _heartRate = MutableStateFlow(0)
    val heartRate: StateFlow<Int> = _heartRate

    fun updateHeartRate(bpm: Int) {
        _heartRate.value = bpm
    }
}