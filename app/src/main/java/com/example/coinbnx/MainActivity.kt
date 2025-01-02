package com.example.coinbnx

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinbnx.Api.CoinApi
import com.example.coinbnx.Component.BottomBar
import com.example.coinbnx.Component.Button_Btn
import com.example.coinbnx.Component.Coins_Box
import com.example.coinbnx.Component.TopBar
import com.example.coinbnx.Component.WalletCard
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.data.coin
import com.example.coinbnx.repository.CoinViewModel

import com.example.coinbnx.ui.theme.CoinBnxTheme
import com.example.coinbnx.ui.theme.blue
import dagger.hilt.android.AndroidEntryPoint

import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coinApi: CoinApi
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch{
            val response = coinApi.getCoins()
            Log.d("response", response.body().toString())
        }
        enableEdgeToEdge()
        setContent {
            CoinBnxTheme {

                val hazeState = remember { HazeState() }
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .background(MaterialTheme.colorScheme.background),
                    containerColor = Color.Transparent,
                    topBar = {
                        Column() {
                            TopBar(
                                modifier = Modifier
                                    .hazeChild(state = hazeState),
                                scrollBehavior = scrollBehavior,
                            )
                        }
                    },

                ){ innerPAdding ->

                    HomeScreen(
                        paddingValues = innerPAdding,
                        hazeState = hazeState,
                    )

                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    paddingValues: PaddingValues,
    coinViewModel: CoinViewModel = hiltViewModel()

) {
    val coins : State<List<CoinX>> = coinViewModel.coins.collectAsState(initial = emptyList())
    Log.d("helo",coins.value.toString())
    Box(
        modifier = modifier.fillMaxHeight()
    ) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))

            // WalletCard Section
            WalletCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(start = 30.dp, end = 30.dp),
                balance = "200.00",
                profit_loss_percentage = "+ 12.3"
            )

            // Button Row
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button_Btn(
                    modifier = Modifier
                        .padding(start = 7.dp, end = 7.dp)
                        .weight(1f)
                        .offset(y = -5.dp),
                    color = blue,
                    btn_text = "Transfer"
                )
                Button_Btn(
                    modifier = Modifier
                        .padding(start = 7.dp, end = 7.dp)
                        .weight(1f)
                        .offset(y = -5.dp),
                    color = MaterialTheme.colorScheme.background,
                    btn_text = "Withdraw"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Popular Crypto Text
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 24.dp, top = 4.dp)
            ) {
                Text(
                    text = "Popular Crypto",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(count = minOf(coins.value.size, 6)) { index -> // Limit to the first 6 items
                    Coins_Box(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 4.dp, top = 4.dp),
                        Coin_Name = coins.value[index].name,
                        Coin_Price = coins.value[index].price.take(6),
                        Coin_Color = coins.value[index].color,
                        Coin_Symbal = coins.value[index].symbol,
                        Coin_Change = coins.value[index].change,
                        imageUrl = coins.value[index].iconUrl
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        BottomBar(
            modifier = Modifier
                .padding(bottom = 20.dp, start = 22.dp, end = 22.dp)
                .clip(RoundedCornerShape(28.dp))
                .fillMaxWidth()
                .align(alignment = Alignment.BottomStart)
        )
    }
}







