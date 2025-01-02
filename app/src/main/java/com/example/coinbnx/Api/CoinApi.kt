package com.example.coinbnx.Api

import com.example.coinbnx.data.coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {
    @GET("coins")
    suspend fun getCoins(): Response<coin>
}