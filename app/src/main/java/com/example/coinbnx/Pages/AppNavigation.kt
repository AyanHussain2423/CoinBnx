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
import com.example.coinbnx.data.CoinX

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    coinList: List<CoinX>
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
        composable("invest/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
            index?.let {
                val coin = coinList.getOrNull(it) // Retrieve the CoinX object by index
                coin?.let { selectedCoin ->
                    InvestScreen(
                        coinX = selectedCoin,
                        paddingValues = paddingValues,
                    ) // Pass the CoinX object to InvestScreen
                }
            }
        }

    }
}
