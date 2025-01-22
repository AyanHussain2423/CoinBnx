package com.example.coinbnx.Pages

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay


const val pinSize = 4
const val password = "1234" //sample password

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun Pinccode_page() {
    val inputPin = remember { mutableStateListOf<Int>() }
    val error = remember { mutableStateOf<String>("") }
    val showSuccess = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (inputPin.size == 4) {
        LaunchedEffect(true) {
            delay(300)

            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                error.value = ""

            } else {
                inputPin.clear()
                error.value = "Wrong pin, Please retry!"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),  // Set the background to black
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Enter pin to unlock",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onBackground  // Set text color to white (onBackground)
                )

                Spacer(modifier = Modifier.height(30.dp))

                if (showSuccess.value) {
                    LottieLoadingView(
                        context = context,
                        file = "success.json",
                        iterations = 1,
                        modifier = Modifier.size(100.dp)
                    )
                } else {
                    Row {
                        (0 until pinSize).forEach {
                            Icon(
                                imageVector = if (inputPin.size > it) Icons.Default.Circle else Icons.Outlined.Circle,
                                contentDescription = it.toString(),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(30.dp),
                                tint = MaterialTheme.colorScheme.onBackground // Set color to white (onBackground)
                            )
                        }
                    }
                }

                Text(
                    text = error.value,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))
            }

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (1..3).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground // Set text color to white (onBackground)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (4..6).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground // Set text color to white (onBackground)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (7..9).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground // Set text color to white (onBackground)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { }
                    )
                    PinKeyItem(
                        onClick = { inputPin.add(0) },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "0",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground // Set text color to white (onBackground)
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Backspace,
                        contentDescription = "Clear",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                if (inputPin.isNotEmpty()) {
                                    inputPin.removeLast()
                                }
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun LottieLoadingView(
    context: Context,
    file: String,
    modifier: Modifier = Modifier,
    iterations: Int = 10
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(file))
    LottieAnimation(
        composition = composition,
        modifier = modifier.defaultMinSize(300.dp),
        iterations = iterations
    )
}

@Composable
fun PinKeyItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(8.dp).border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.onBackground,
        shape = RoundedCornerShape(16.dp) // Apply rounded corners with 16dp radius
    ),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp), // Ensure rounded corners here too
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 64.dp, minHeight = 64.dp)
                .padding(8.dp), // Adding some padding for inner content
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Preview(showBackground = true)
@Composable
fun PreviewPinCodePage() {
    Pinccode_page()
}
