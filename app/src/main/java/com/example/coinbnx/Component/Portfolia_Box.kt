package com.example.coinbnx.Component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.data.Firebase_Coin
import com.example.coinbnx.repository.CoinViewModel

@Composable
fun Portfolio_Coin_Box(
    modifier: Modifier = Modifier,
    coin: Firebase_Coin,
    coinViewModel: CoinViewModel = hiltViewModel()
) {
    val coins : State<List<CoinX>> = coinViewModel.coins.collectAsState(initial = emptyList())
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(coin.iconUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    )

    val indexvalue = coin.index.toInt()
    var profit = remember(coins.value, indexvalue, coin){
        val selectedCoin = coins.value.getOrNull(indexvalue)
        if (selectedCoin != null) {
            selectedCoin.price.toDouble() * coin.coin_Quantity.toDouble() - coin.price_in_Dollers.toDouble()
        } else {
            0.0 // Default value in case of invalid index
        }
    }
    var colour = remember(profit) {
        if (profit >0){
            Color.Green
        }
        else{
            Color.Red
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Box(
        modifier = modifier
            .fillMaxHeight(0.2f)  // Height is adjusted dynamically
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
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
                    text = "${coin.coin_Quantity.take(4)}",
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
                    text = "$${coin.price_in_Dollers}",  // Replace with real data if available
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Returns $${profit.toString().take(6)}",  // Replace with real data if available
                    color = colour,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,

                )
            }
        }
    }
}