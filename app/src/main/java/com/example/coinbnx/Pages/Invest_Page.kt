package com.example.coinbnx.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Import necessary libraries for Compose and Material3
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coinbnx.Component.BottomBar
import com.example.coinbnx.Component.Coins_Box
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.repository.CoinViewModel

@Composable
fun Invest_Page(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    coinViewModel: CoinViewModel = hiltViewModel(),
) {
    val coins : State<List<CoinX>> = coinViewModel.coins.collectAsState(initial = emptyList())
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        modifier = modifier.fillMaxHeight()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            LazyColumn {
                items(count = minOf(coins.value.size)) { index -> // Limit to the first 6 items
                    Coins_Box(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 4.dp,
                            top = 4.dp
                        ),
                        coin = coins.value[index],
                        index = index,  // Pass the index
                        navController = navController,
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InvestPagePreview() {
    val navController = rememberNavController()  // Mock NavController
    val paddingValues = PaddingValues(16.dp)  // Mock PaddingValues

    Surface {
        Invest_Page(
            modifier = Modifier,
            navController = navController,
            paddingValues = paddingValues
        )
    }
}
