package com.example.coinbnx.Pages

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coinbnx.Component.Portfolio_Coin_Box
import com.example.coinbnx.repository.FirebaseViewModel

@Composable
fun Portfolia_Page(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),

) {
    val coinNamesState = firebaseViewModel.coinNames.collectAsState(initial = emptyList())
    val coinList = firebaseViewModel.coinListState.collectAsState()
    firebaseViewModel.fetchCoinData(coinNamesState.value)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Use padding values if needed
    ) {
        // Title at the top of the list
        item {
            Text(
                text = "Your Assets",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            )
        }

        // List of coins
        items(count = coinList.value.size) { index ->
            Portfolio_Coin_Box(
                coin = coinList.value[index]
            )
        }
    }
}