package com.example.coinbnx.data

import androidx.compose.ui.text.input.TextFieldValue
import com.google.firebase.database.snapshot.Index

data class Firebase_Coin(
    val iconUrl: String,
    val name: String,
    val symbol: String,
    val coin_Quantity: String,
    val price_in_Dollers: String,
    val Coin_Bought_Price: String,
    val index: String
){

    constructor() : this(
        iconUrl = "",
        name = "",
        symbol = "",
        coin_Quantity = "",
        price_in_Dollers = "",
        Coin_Bought_Price = "",
        index = "",
    )
}