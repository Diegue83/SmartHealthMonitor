package mx.utng.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import mx.utng.tv.presentation.TvCatalogScreen
import mx.utng.tv.presentation.TvViewModel
import mx.utng.tv.presentation.TvViewModelFactory
import mx.utng.tv.ui.theme.SmartHealthMonitorTheme

class TvMainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHealthMonitorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    val navController = rememberNavController()
                    
                    // Inicializar ViewModel con Factory para inyectar contexto
                    val viewModel: TvViewModel = viewModel(
                        factory = TvViewModelFactory(applicationContext)
                    )

                    NavHost(navController = navController, startDestination = "catalog") {
                        composable("catalog") {
                            TvCatalogScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
