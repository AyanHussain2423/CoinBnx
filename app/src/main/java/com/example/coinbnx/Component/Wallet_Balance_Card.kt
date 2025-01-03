package com.example.coinbnx.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.ui.theme.CoinBnxTheme
import com.example.coinbnx.R

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    balance: String,
    profit_loss_percentage: String
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFF8D8D8D),
                        Color(0xFF1C1C1C)// Black
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 1f)
                )
            )
            .padding(2.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(14.dp)), // Ensure the Card's corners match the Box's
            elevation = CardDefaults.cardElevation(24.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF),
                                Color(0xFF8D8D8D),
                                Color(0xFF1C1C1C)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(1f, 1f) // Diagonal gradient
                        )
                    )
            ) {
                // Background Gradient
                Image(
                    painter = painterResource(R.drawable.purpletrans),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.orangetrans),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 26.dp, top = 36.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Current Balance",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "$${balance}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.4f))
                        Row(
                            modifier = Modifier.padding(bottom = 22.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profit),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(18.dp)
                                    .height(18.dp)
                            )
                            Text(
                                text = "${profit_loss_percentage}",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.Green,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2f, 2f),
                                    )
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Today's Growth",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                    Column {
                        Image(
                            painter = painterResource(R.drawable.bitcoinsecond),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth(0.4f)
                                .offset(y = -12.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.bitcoinfirst),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(0.9f)
                                .fillMaxWidth(0.9f)
                                .offset(y = -6.dp, x = 4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    CoinBnxTheme {
        WalletCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            balance = "100.00",
            profit_loss_percentage = "+13.2"
        )
    }
}
