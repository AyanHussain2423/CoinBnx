package com.example.coinbnx.Component

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinbnx.Pages.InvestScreen
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.data.coin
import com.example.coinbnx.ui.theme.CoinBnxTheme

@Composable
fun Coins_Box(
    modifier: Modifier = Modifier,
    coin: CoinX,
    index: Int,
    navController: NavController,
) {

    // Convert the hex color code to a Color
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(coin.iconUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    )

    Box(
        modifier = modifier
            .height(65.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable{
                navController.navigate("invest/$index")
            }
    ) {
        // Background Blur Effect
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(MaterialTheme.colorScheme.background)
                .blur(10.dp)
        )

        // Content Layer (Image and Text)
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(6.dp), // Adjust padding here for internal spacing
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                            .padding(end = 8.dp) // Space between image and text
                    )
                }

                // Coin Information (Name, Price)
                Column(
                    modifier = Modifier.weight(1f), // This will ensure the text takes up available space
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = coin.name,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f) // Ensures the name and price are spaced properly
                        )
                        Text(
                            text = "$${coin.price.take(6)}",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp)) // Space between rows

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = coin.symbol,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Space between symbol and change

                        val coinValue = coin.change.toFloatOrNull() ?: 0f // Default to 0 if conversion fails
                        val textColor = if (coinValue < 0) Color.Red else Color.Green

                        Text(
                            text = "${coin.change} %",
                            color = textColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }

        // Divider for separation
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 5.dp, end = 15.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
            thickness = 0.5.dp
        )
    }
}

fun Color.Companion.fromHex(hex: String): Color {
    return try {
        Color(AndroidColor.parseColor(hex.trim()))
    } catch (e: IllegalArgumentException) {
        Color(0xFF000000) // Default fallback color (black)
    }
}

@Preview
@Composable
fun preview() {
    CoinBnxTheme {

    }
}