package mx.utng.tv.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.utng.shared.data.SmartHealthRepository
import mx.utng.tv.domain.model.TvUiState

class TvViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState())
    val uiState: StateFlow<TvUiState> = _uiState.asStateFlow()

    init {
        observarDatos()
    }

    private fun observarDatos() {
        viewModelScope.launch {
            // 1. Recolectar historial
            SmartHealthRepository.historial
                .onEach { lista -> 
                    _uiState.update { it.copy(lecturas = lista, isLoading = false) } 
                }
                .launchIn(this)

            // 2. Recolectar FC Actual
            SmartHealthRepository.fcFlow
                .onEach { fc -> 
                    _uiState.update { it.copy(fcActual = fc) } 
                }
                .launchIn(this)

            // Asegurar que la pantalla no se quede en "Cargando"
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
