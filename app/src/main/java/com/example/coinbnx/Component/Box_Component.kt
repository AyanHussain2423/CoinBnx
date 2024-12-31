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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.R
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
            .clip(RoundedCornerShape(30.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background Box with Blur effect
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.linearGradient(colors = listOf(Color(0xFF1D1B32), Color(0xFF2C2A3B)))) // Gradient Background
                .blur(20.dp) // Applying blur to the background
                .clip(RoundedCornerShape(30.dp))
        )

        // Semi-transparent Overlay for additional effect
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.4f)) // Semi-transparent overlay
                .clip(RoundedCornerShape(30.dp))
                .padding(8.dp)
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

@Preview
@Composable
fun PreviewTransparentBlurredBox() {
    CoinBnxTheme {
        TransparentBlurredBox(
            modifier = Modifier
                .height(150.dp)
                .width(180.dp),
            title = "Top Gainers",
            icon = R.drawable.profitgraph, // Replace with your icon resource
        )
    }
}
