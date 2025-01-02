package com.example.coinbnx.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.data.coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(private val coinRepository: CoinRepositrory) : ViewModel() {

    val coins: StateFlow<List<CoinX>>
    get() = coinRepository.coins

    init {
        // Fetch coins when the ViewModel is created
        viewModelScope.launch {
            coinRepository.getCoins()
        }
    }
}