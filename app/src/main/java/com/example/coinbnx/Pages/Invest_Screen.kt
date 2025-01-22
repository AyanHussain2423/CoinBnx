package com.example.coinbnx.Pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.Component.Button_Btn
import com.example.coinbnx.Component.Invest_Coin_Box
import com.example.coinbnx.Component.SparklineChart
import com.example.coinbnx.data.CoinX
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dev.chrisbanes.haze.HazeState

// Write a message to the database
val database = Firebase.database
val myRef = database.getReference()

@Composable
fun InvestScreen(
    modifier: Modifier = Modifier,
    coinX: CoinX,
    sparklineData: List<Float>,
    paddingValues: PaddingValues
) {
    val filteredData = sparklineData.filterNotNull()
    val minValue = filteredData.minOrNull()?.toFloat() ?: 0f
   val maxValue = filteredData.maxOrNull()?.toFloat() ?: 0f

    val changeValue = coinX.change.toFloatOrNull() ?: 0f

    // Define the color based on whether the change is positive or negative
    val changeColor = if (changeValue > 0) Color.Green else if (changeValue < 0) Color.Red else Color.Gray

    val coinlist = listOf<String>(
        coinX.symbol,
        coinX.price,
        coinX.name,
        coinX.iconUrl,
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Coin Price Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = coinX.price.take(7),
                                fontWeight = FontWeight.Medium,
                                fontSize = 32.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "1 ${coinX.symbol}",
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(changeColor)
                                .padding(horizontal = 16.dp, vertical = 5.dp)
                                .wrapContentWidth()
                        ) {
                            Text(
                                text = coinX.change,
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                   SparklineChart(
                        sparklineData = coinX.sparkline.filterNotNull(),
                        lineColor = changeColor,
                        backgroundColor = Color.Transparent,
                        change = coinX.change
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Coin Overview Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Coin Overview",
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp)
                    )
                    // Overview Rows
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "24 Volume:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = coinX.`24hVolume`,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Marketcap:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = coinX.marketCap,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Tier Rank:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = coinX.tier.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Coin Symbol:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = coinX.symbol,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "24h Min:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = minValue.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "24h Max:",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = maxValue.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Text(
                text = "Your Assets",
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp, start = 4.dp)
            )
            Invest_Coin_Box(
                coin = coinX
            )
        }

        // Buy/Sell Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button_Btn(
                modifier = Modifier
                    .padding(end = 7.dp)
                    .weight(1f),
                color = Color.Green,
                btn_text = "Buy",
                onClick = {
                    buy(
                        coinlist
                    )
                }
            )
            Button_Btn(
                modifier = Modifier
                    .padding(end = 7.dp)
                    .weight(1f),
                color = Color.Red,
                btn_text = "Sell",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInvestScreen() {
    val sampleCoinX = CoinX(
        symbol = "BTC",
        price = "50,000.00",
        change = "+5.23",
        sparkline = listOf(50000f, 51000f, 52000f, 51500f, 53000f),
        `24hVolume` = "2,000,000,000",
        btcPrice = "50,000.00",
        coinrankingUrl = "https://www.coinranking.com/coin/btc-bitcoin",
        color = Color(0xFFf2a900).toString(),
        contractAddresses = listOf("0x1234...", "0x5678..."),
        iconUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png",
        listedAt = 20130428,
        lowVolume = false,
        marketCap = "900,000,000,000",
        name = "Bitcoin",
        rank = 1,
        tier = 1,
        uuid = "Qwsogvfrza",
    )

    InvestScreen(
        coinX = sampleCoinX,
        paddingValues = PaddingValues(10.dp),
        sparklineData = sampleCoinX.sparkline
    )
}
fun buy(
    coinlist: List<String>
) {
    myRef.child("User").child("Asset").setValue(coinlist)
}

fun sell(){

}