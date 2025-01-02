package com.example.coinbnx.repository

import com.example.coinbnx.Api.CoinApi
import com.example.coinbnx.data.CoinX
import com.example.coinbnx.data.coin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositrory @Inject constructor(private val coinApi: CoinApi) {

    private val _coins = MutableStateFlow<List<CoinX>>(emptyList())
    val coins: StateFlow<List<CoinX>>
        get() =  _coins // Exposing a read-only StateFlow

    // Suspend function to fetch coins from the API
    suspend fun getCoins() {
        try {
            // Call the API
            val response = coinApi.getCoins()

            if (response.isSuccessful && response.body() != null) {
                _coins.emit(response.body()!!.data.coins)
            } else {
                // Handle failure, maybe by updating _coins with an empty list or an error message
                _coins.value = emptyList() // Or you could manage errors differently
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., network errors)
            _coins.value = emptyList() // Or manage error states accordingly
        }
    }
}