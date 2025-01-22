import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.coinbnx.HomeScreen
import com.example.coinbnx.Pages.InvestScreen
import com.example.coinbnx.Pages.Invest_Page
import com.example.coinbnx.Pages.SignUpUI
import com.example.coinbnx.data.CoinX
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    coinList: List<CoinX>,
    firebaseAuth: FirebaseAuth
) {
    // Track the starting destination dynamically based on authentication state
    val currentUser = firebaseAuth.currentUser
    val startDestination = if (currentUser != null) "home" else "signin"

    NavHost(
        navController = navController,
        startDestination = startDestination
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
                        sparklineData = selectedCoin.sparkline
                    )
                }
            }
        }
        composable("invest_page") {
            Invest_Page(
                navController = navController,
                paddingValues = paddingValues
            )
        }
        composable("signin") {
            SignUpUI(
                navController = navController
            )
        }
    }
}
