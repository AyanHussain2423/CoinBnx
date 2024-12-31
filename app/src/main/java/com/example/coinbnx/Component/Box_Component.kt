package com.example.coinbnx.Component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.R
import com.example.coinbnx.ui.theme.Blur
import com.example.coinbnx.ui.theme.CoinBnxTheme

@Composable
fun TransparentBlurredBox(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.gradientimage),
            contentDescription = "Background Image",
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(30.dp) // Adjust blur radius as needed
                .background(Color.Black.copy(0.4f)) // Semi-transparent black overlay for blur effect
                .clip(RoundedCornerShape(30.dp))
        )

        // Content Layer
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title Text
            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            // Icon
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Transparent),
            )
        }
    }
}

@Composable
fun TestBlurredBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1B32))
    ) {

    }
}
@Preview
@Composable
fun PreviewTransparentBlurredBox() {
    CoinBnxTheme {
        TestBlurredBox()
        TransparentBlurredBox(
            modifier = Modifier
                .height(150.dp)
                .width(180.dp),
            title = "Top Gainers",
            icon = R.drawable.profitgraph,
        )
    }
}
