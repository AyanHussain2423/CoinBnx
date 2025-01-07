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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coinbnx.Component.Button_Btn
import com.example.coinbnx.Component.SparklineChart
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.ui.theme.CoinBnxTheme
import com.example.coinbnx.ui.theme.blue

@Composable
fun InvestScreen(
    modifier: Modifier = Modifier,
    coinX: CoinX
) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background.copy(0.6f))
                    .padding(20.dp)
            ){
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = coinX.price,
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
                                .background(Color.Green)
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
                        lineColor = TODO(),
                        backgroundColor = TODO()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInvestScreen() {
    CoinBnxTheme {

    }
}
