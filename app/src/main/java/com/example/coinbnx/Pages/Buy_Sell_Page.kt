package com.example.coinbnx.Pages

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.ui.theme.CoinBnxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buy_Sell_Page(
    modifier: Modifier = Modifier,
    coinX: CoinX,
    navController: NavController
) {
    Log.d("HAhahaha",coinX.name)
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(coinX.iconUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    )
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background.copy(0.10f),
                scrolledContainerColor = Color.Transparent,
            ),
            title = {
                Row(
                    modifier = Modifier
                        .height(40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .padding(top = 4.dp, bottom = 4.dp)
                            .clickable{
                                navController.popBackStack()
                            },
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painter,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .padding(4.dp)
                    )
                    Text(
                        text = coinX.name.toString(),
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(end = 40.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estimated Buying Price",
                fontWeight = FontWeight.Light,
                fontSize = 8.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                modifier = Modifier.padding(end = 30.dp)
            )
            Text(
                text = coinX.price.take(8),
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = 30.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.2f),
                    thickness = 1.dp
                )
                Text(
                    text = "How Much Do You Want To Buy?",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.2f),
                    thickness = 1.dp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
            AnimatedAmountTextField(
                coin_price = coinX.price
            )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedAmountTextField(
    coin_price: String
) {
    var Dollers by remember { mutableStateOf(TextFieldValue("")) }
    var Coin_Amount by remember { mutableStateOf(TextFieldValue("")) }
    var focused1 by remember { mutableStateOf(false) }
    var focused2 by remember { mutableStateOf(false) }
    var Bitcoin by remember { mutableStateOf("") }

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Blue, Color.Cyan)
    )

    // Function to update Coin Amount based on Dollers and coin_price
    fun updateCoinAmount() {
        val dollerAmount = Dollers.text.toDoubleOrNull() ?: 0.0
        val coinPrice = coin_price.toDoubleOrNull() ?: 1.0
        if (coinPrice != 0.0 && dollerAmount > 0) {
            val calculatedCoinAmount = dollerAmount / coinPrice
            Coin_Amount = TextFieldValue(String.format("%.6f", calculatedCoinAmount)) // Update Coin_Amount
        } else {
            Coin_Amount = TextFieldValue("") // Clear Coin_Amount if input is invalid or zero
        }
    }

    // Function to update Dollers based on Coin Amount and coin_price
    fun updateDollers() {
        val coinAmount = Coin_Amount.text.toDoubleOrNull() ?: 0.0
        val coinPrice = coin_price.toDoubleOrNull() ?: 1.0 // Get coin price
        if (coinPrice != 0.0 && coinAmount > 0) {
            val calculatedDollers = coinAmount * coinPrice
            Dollers = TextFieldValue(String.format("%.2f", calculatedDollers)) // Update Dollers
        } else {
            Dollers = TextFieldValue("") // Clear Dollers if input is invalid or zero
        }
    }

    // Call updateCoinAmount whenever Dollers value changes
    LaunchedEffect(Dollers) {
        if (Dollers.text.isNotEmpty()) { // Only update Coin_Amount if Dollers is not empty
            updateCoinAmount()
        }
    }

    // Call updateDollers whenever Coin_Amount value changes
    LaunchedEffect(Coin_Amount) {
        if (Coin_Amount.text.isNotEmpty()) { // Only update Dollers if Coin_Amount is not empty
            updateDollers()
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight(0.1f)
            ) {
                TextField(
                    value = Dollers,
                    onValueChange = {
                        Dollers = it
                    },
                    label = {
                        Text("In Dollers", style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                    },
                    modifier = Modifier
                        .padding(4.dp) // Padding inside the TextField
                        .onFocusChanged {
                            focused1 = it.isFocused
                        }
                        .drawBehind {
                            // Custom gradient border when focused
                            if (focused1) {
                                val borderWidth = 2.dp.toPx()
                                val width = size.width
                                val height = size.height
                                val cornerRadius = 8.dp.toPx()

                                drawRoundRect(
                                    brush = gradientBrush,
                                    size = androidx.compose.ui.geometry.Size(width, height),
                                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                                    style = androidx.compose.ui.graphics.drawscope.Stroke(borderWidth)
                                )
                            } else {
                                val borderWidth = 2.dp.toPx()
                                val width = size.width
                                val height = size.height
                                val cornerRadius = 8.dp.toPx()

                                drawRoundRect(
                                    color = Color.Gray,
                                    size = androidx.compose.ui.geometry.Size(width, height),
                                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                                    style = androidx.compose.ui.graphics.drawscope.Stroke(borderWidth)
                                )
                            }
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,  // Remove the default indicator color
                        unfocusedIndicatorColor = Color.Transparent // Remove the default indicator color
                    ),
                    textStyle = TextStyle(fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }

            Text(
                text = "=",
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight(0.1f)
            ) {
                TextField(
                    value = Coin_Amount,
                    onValueChange = {
                        Coin_Amount = it
                    },
                    label = {
                        Text("In Coins", style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                    },
                    modifier = Modifier
                        .padding(4.dp) // Padding inside the TextField
                        .onFocusChanged {
                            focused2 = it.isFocused
                        }
                        .drawBehind {
                            // Custom gradient border when focused
                            if (focused2) {
                                val borderWidth = 2.dp.toPx()
                                val width = size.width
                                val height = size.height
                                val cornerRadius = 8.dp.toPx()

                                drawRoundRect(
                                    brush = gradientBrush,
                                    size = androidx.compose.ui.geometry.Size(width, height),
                                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                                    style = androidx.compose.ui.graphics.drawscope.Stroke(borderWidth)
                                )
                            } else {
                                val borderWidth = 2.dp.toPx()
                                val width = size.width
                                val height = size.height
                                val cornerRadius = 8.dp.toPx()

                                drawRoundRect(
                                    color = Color.Gray,
                                    size = androidx.compose.ui.geometry.Size(width, height),
                                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                                    style = androidx.compose.ui.graphics.drawscope.Stroke(borderWidth)
                                )
                            }
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,  // Remove the default indicator color
                        unfocusedIndicatorColor = Color.Transparent // Remove the default indicator color
                    ),
                    textStyle = TextStyle(fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }

    }
}



@Composable
@Preview
fun prev() {
    CoinBnxTheme {
        val sampleCoinX = CoinX(
            symbol = "BTC",
            price = "50,000.00",
            change = "+5.23",
            sparkline = listOf(50000f, 51000f, 52000f, 51500f, 53000f),
            `24hVolume` = "2,000,000,000",
            btcPrice = "50,000.00",
            coinrankingUrl = "https://www.coinranking.com/coin/btc-bitcoin",
            color = Color(0xFFf2a900).toString(),
            contractAddresses = listOf("0x1234...", "0x5678..."),
            iconUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png",
            listedAt = 20130428,
            lowVolume = false,
            marketCap = "900,000,000,000",
            name = "Bitcoin",
            rank = 1,
            tier = 1,
            uuid = "Qwsogvfrza",
        )

        Buy_Sell_Page(
            coinX = sampleCoinX,
            navController = TODO()
        )
    }
}
