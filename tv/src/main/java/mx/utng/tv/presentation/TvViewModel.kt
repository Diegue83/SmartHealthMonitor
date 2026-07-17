package mx.utng.tv.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
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
            // 1. Recolectar historial (desde Room DAO vía Repository)
            SmartHealthRepository.historial
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { e -> _uiState.update { it.copy(error = e.message, isLoading = false) } }
                .onEach { lista -> 
                    _uiState.update { it.copy(lecturas = lista, isLoading = false) } 
                }
                .launchIn(this)

            // 2. Recolectar FC Actual (StateFlow)
            SmartHealthRepository.fcFlow
                .onEach { fc -> 
                    _uiState.update { it.copy(fcActual = fc) } 
                }
                .launchIn(this)
        }
    }
}
