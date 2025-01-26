package com.example.coinbnx.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(count = coinList.value.size) { index ->
                Portfolio_Coin_Box(
                    coin = coinList.value[index]
                )
            }
        }
    }
}