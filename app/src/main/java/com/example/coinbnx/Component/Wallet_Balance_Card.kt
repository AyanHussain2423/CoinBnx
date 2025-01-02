package com.example.coinbnx.Component

import android.R.attr.enabled
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .height(110.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
    ) {
        // Background image (gradient)
        Image(
            painter = painterResource(R.drawable.gradientimage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Semi-transparent overlay with blur effect
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(10.dp)  // Applying blur effect
                .background(Color.Black.copy(alpha = 0.2f))  // Semi-transparent dark overlay for better contrast
                .clip(RoundedCornerShape(10.dp))
        )

        // Content Layer (Text)
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(10.dp), // Adds padding around the content
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Current Balance",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text ="$${balance}",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp),
                        tint = Color.Green
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text ="${profit_loss_percentage} %",
                        color = Color.Green,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.offset(y=1.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        // Handle button click
                    },
                    modifier = Modifier
                        .height(40.dp)  // Use a slightly taller height for better usability
                        .width(155.dp)  // Adjust width to accommodate text properly
                        .padding(horizontal = 16.dp), // Optional: Add padding inside button for better touch targets
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,  // Set the background color
                        contentColor = MaterialTheme.colorScheme.background  // Set text color for contrast
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(25.dp)
                ) {
                    Text(
                        text = "Add",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
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
