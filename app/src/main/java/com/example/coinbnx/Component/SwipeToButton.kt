package com.example.coinbnx.Component

import android.service.autofill.OnClickAction
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coinbnx.data.Firebase_Coin
import com.example.coinbnx.ui.theme.CoinBnxTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

val GreenColor = Color(0xFF2FD286)

enum class ConfirmationState {
    Default, Confirmed
}

val database = FirebaseDatabase.getInstance()
val myRef: DatabaseReference = database.getReference()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmationButton(
    modifier: Modifier = Modifier,
    firebaseCoin: Firebase_Coin,
    navController: NavController,
) {
    val width = 350.dp
    val dragSize = 50.dp
    var swipeableState = rememberSwipeableState(ConfirmationState.Default)
    val sizePx = with(LocalDensity.current) { (width - dragSize).toPx() }
    val anchors = mapOf(0f to ConfirmationState.Default, sizePx to ConfirmationState.Confirmed)

    // Track the swipe progress
    val progress = derivedStateOf {
        if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
    }

    // State for confirmation (True when the operation is complete)
    var isConfirmed by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    // Handle the swipe gesture and Firebase task
    LaunchedEffect(progress.value) {
        if (progress.value >= 0.99f && !isLoading) {
            isLoading = true
            myRef.child("Coins")
                .child("Asset")
                .child(firebaseCoin.name)
                .setValue(firebaseCoin)
                .addOnCompleteListener { task ->
                    isLoading = false
                    if (task.isSuccessful) {
                        isConfirmed = false
                        navController.navigate("portfolio_page"){
                            popUpTo("home") { inclusive = true }
                            launchSingleTop = true
                        }

                    } else {
                        isConfirmed = false
                    }
                }
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(GreenColor, RoundedCornerShape(dragSize))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Swipe To Buy", color = Color.White, fontSize = 18.sp)
        }

        DraggableControl(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(dragSize),
            progress = progress.value,
            isConfirmed = isConfirmed,
            isLoading = isLoading
        )
    }
}

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float,
    isConfirmed: Boolean,
    isLoading: Boolean
) {
    Box(
        modifier
            .padding(4.dp)
            .shadow(elevation = 2.dp, CircleShape, clip = false)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // Use Crossfade to show different states (loading, confirmation, or swipe)
        Crossfade(targetState = isConfirmed) { isConfirmedState ->
            when {
                isConfirmedState -> {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null,
                        tint = GreenColor
                    )
                }
                isLoading -> {
                    // Show a loading spinner while the task is in progress
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = GreenColor,
                        strokeWidth = 2.dp
                    )
                }
                else -> {
                    // Show the default swipe icon
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = null,
                        tint = GreenColor
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun prev() {
    CoinBnxTheme {
        // Provide a sample Firebase_Coin for preview
        val sampleCoin = Firebase_Coin(
            name = "Bitcoin",
            symbol = "BTC",
            iconUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png",
            coin_Quantity = "1",
            price_in_Dollers = "2",
            Coin_Bought_Price = "2",
            index = "1"
        )
        ConfirmationButton(
            firebaseCoin = sampleCoin,
            navController = rememberNavController()
        )
    }
}
