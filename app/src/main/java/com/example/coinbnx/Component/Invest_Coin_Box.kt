package com.example.coinbnx.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.repository.CoinViewModel
import com.example.coinbnx.repository.FirebaseViewModel
import com.google.firebase.database.snapshot.Index

@Composable
fun Invest_Coin_Box(
    modifier: Modifier = Modifier,
    coin: CoinX,
    index: Int,
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    val coinNamesState = firebaseViewModel.coinNames.collectAsState(initial = emptyList())
    val coinList = firebaseViewModel.coinListState.collectAsState()
    firebaseViewModel.fetchCoinData(coinNamesState.value)


    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(coin.iconUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    )

    Box(
        modifier = modifier
            .height(100.dp)  // Height is adjusted dynamically
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                // Handle click action
            }
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)) // Light background for better visibility
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp) // Padding for internal spacing
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp), // Spacing between rows
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Image Section
                if (coin.iconUrl != null) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp) // Adjust the image size as needed
                            .padding(end = 12.dp) // Space between image and text
                    )
                }

                // Coin Information (Symbol and Name)
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = coin.symbol,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier// Ensures proper spacing between name and price
                    )
                    Text(
                        text = coin.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                // Coin Price
                Text(
                    text = "$${coin.price}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically) // This ensures the price is vertically centered
                )
            }

            // Divider
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                thickness = 1.dp
            )

            // Invested and Returns Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Invested $1000",  // Replace with real data if available
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Returns $50",  // Replace with real data if available
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvestCoinBoxPreview() {
    val mockCoin = CoinX(
        symbol = "BTC",
        price = "50000.00",
        name = "Bitcoin",
        change = "+5.2%",
        iconUrl = "https://www.example.com/bitcoin_icon.svg", // Replace with an actual URL or local image for testing
        `24hVolume` = "100000000", // Mock volume (e.g., "100 million")
        btcPrice = "50000.00", // Mock BTC price
        coinrankingUrl = "https://www.coinranking.com", // Example URL
        color = "#FF9900", // Example color in hex format
        contractAddresses = listOf("0x12345"), // Example contract address, if applicable
        listedAt = 1325416, // Example timestamp for when the coin was listed
        lowVolume = false, // Example low volume flag
        marketCap = "900000000000", // Example market cap in USD
        rank = 1, // Rank of the coin (e.g., Bitcoin being rank 1)
        sparkline = listOf(50000f, 51000f, 52000f, 51500f, 53000f), // Example sparkline data (price history)
        tier = 1, // Example tier
        uuid = "bitcoin-uuid" // Example unique identifier
    )

    // Display the Invest_Coin_Box with the mock data
    Invest_Coin_Box(
        coin = mockCoin,
        modifier = Modifier.padding(16.dp),
        index = 1
    )
}
