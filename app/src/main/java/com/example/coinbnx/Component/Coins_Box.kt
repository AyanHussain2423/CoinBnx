package com.example.coinbnx.Component

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.ui.theme.CoinBnxTheme
import com.example.coinbnx.R
import com.example.coinbnx.ui.theme.blue

@Composable
fun Coins_Box(
    modifier: Modifier = Modifier,
    //coinimage : Image,
    Coin_Name: String,
    Coin_Price: String
){
    Box(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
    ){
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                    colors = listOf(blue.copy(0.7f), Color(0xFF8A73FF).copy(0.3f)))
                )
                .blur(80.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(0.3f))
                .blur(30.dp)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(1.dp)
        ){
            Row(
                modifier = Modifier
                    .matchParentSize(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.bitcoin),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(30.dp)),
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = Coin_Name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${Coin_Price}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}


@Preview
@Composable
fun preview(){
    CoinBnxTheme {
        Coins_Box(
            Coin_Name = "Bitcoin",
            Coin_Price = "93000"
        )
    }
}