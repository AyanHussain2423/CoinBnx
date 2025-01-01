package com.example.coinbnx.data

data class CoinX(
    val `24hVolume`: String,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val contractAddresses: List<String>,
    val iconUrl: String,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val price: String,
    val rank: Int,
    val sparkline: List<String>,
    val symbol: String,
    val tier: Int,
    val uuid: String
)