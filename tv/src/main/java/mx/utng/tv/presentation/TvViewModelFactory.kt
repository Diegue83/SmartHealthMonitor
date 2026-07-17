package mx.utng.tv.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.utng.shared.data.SmartHealthRepository

class TvViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvViewModel::class.java)) {
            // Inicializar repositorio con el contexto si es necesario
            SmartHealthRepository.init(context.applicationContext)
            return TvViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
