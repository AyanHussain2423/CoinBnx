package com.example.coinbnx.Component

import androidx.compose.animation.core.ArcMode
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinbnx.ui.theme.Blur
import com.example.coinbnx.ui.theme.CoinBnxTheme

@Composable
fun Button_Btn(
    modifier: Modifier = Modifier,
    btn_text: String,
    color: Color
) {
    Button(
        onClick = { },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        elevation = ButtonDefaults.buttonElevation(2.dp),
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize(Alignment.Center),
            text = btn_text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
fun Previewe() {
    CoinBnxTheme {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
            verticalAlignment = Alignment.CenterVertically // Align vertically in the center
        ) {
            Button_Btn(
                modifier = Modifier.weight(1f), // Distribute width evenly
                color = Color.Blue,
                btn_text = "Deposit"
            )
            Button_Btn(
                modifier = Modifier.weight(1f),
                color = Color.Black,
                btn_text = "Withdraw"
            )
        }
    }
}
