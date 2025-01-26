package com.example.coinbnx.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbnx.data.FCoin
import com.example.coinbnx.data.Firebase_Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    // Using StateFlow to expose the list of coin names
    private val _coinNames = MutableStateFlow<List<String>>(emptyList())
    val coinNames: StateFlow<List<String>> get() = _coinNames


    init {
        fetchCoinNames()
    }

    private fun fetchCoinNames() {
        repository.getCoinNames().observeForever { names ->
            _coinNames.value = names
        }
    }

    private val _coinListState = MutableStateFlow<List<Firebase_Coin>>(emptyList())
    val coinListState: StateFlow<List<Firebase_Coin>> get() = _coinListState

    // Function to fetch coin data and update the state
    fun fetchCoinData(coinNames: List<String>) {
        viewModelScope.launch {
            // Call repository to fetch the coin data
            repository.fetchCoinsData(coinNames) { fetchedCoins ->
                // Update the state with the fetched coins list
                _coinListState.value = fetchedCoins
            }
        }
    }
}




