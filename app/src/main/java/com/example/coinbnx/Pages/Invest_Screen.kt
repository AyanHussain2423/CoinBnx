package com.example.coinbnx.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.coinbnx.Component.SparklineChart
import com.example.coinbnx.data.CoinX

@Composable
fun InvestScreen(
    modifier: Modifier = Modifier,
    coinX: CoinX,
    paddingValues: PaddingValues
) {
    val changeValue = coinX.change.toFloatOrNull() ?: 0f

    // Define the color based on whether the change is positive or negative
    val changeColor = if (changeValue > 0) Color.Green else if (changeValue < 0) Color.Red else Color.Gray
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
            ){
                Column {
                    Row( modifier = Modifier.fillMaxWidth().padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = coinX.price.take(7),
                                fontWeight = FontWeight.Medium,
                                fontSize = 32.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = " 1 ${coinX.symbol}",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(changeColor)
                                .padding(horizontal = 16.dp, vertical = 2.dp)
                                .wrapContentWidth()
                        ) {
                            Text(
                                text = coinX.change, // Example text
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                    }
                    SparklineChart(
                        sparklineData = coinX.sparkline,
                        lineColor = changeColor,
                        backgroundColor = Color.Transparent,
                        change = coinX.change
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f)),
            ){
                Column (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Coin Overview",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                        modifier = Modifier.padding(10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "24 Volume:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = coinX.`24hVolume`,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Marketcap:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text =coinX.marketCap,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(
                            text ="Tier Rank:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text =coinX.tier.toString(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text ="Coin Symbol:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text =coinX.symbol,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
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
        color = Color(0xFFf2a900).toString(), // Example color
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
        paddingValues = PaddingValues(20.dp)
    )
}
