import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coinbnx.HomeScreen
import com.example.coinbnx.Pages.InvestScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    // NavHost handles navigation based on the selected screen
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Define your screens and pass the paddingModifier
        composable("home") {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues
            )
        }

    }
}
