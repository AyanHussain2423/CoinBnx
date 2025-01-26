package com.example.coinbnx.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coinbnx.data.Firebase_Coin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val database: DatabaseReference
) {


    fun getCoinNames(): LiveData<List<String>> {
        val coinNamesLiveData = MutableLiveData<List<String>>()

        // Access the "Coins" node, then the "Asset" child.
        val coinsRef = database.child("Coins").child("Asset")

        coinsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val coinNames = mutableListOf<String>()
                for (childSnapshot in snapshot.children) {
                    val coinName = childSnapshot.key ?: continue
                    coinNames.add(coinName)
                }
                coinNamesLiveData.value = coinNames
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle cancellation, you can log the error or show a message
            }
        })

        return coinNamesLiveData
    }

    suspend fun fetchCoinsData(coinNames: List<String>, onResult: (List<Firebase_Coin>) -> Unit) {
        val list = mutableListOf<Firebase_Coin>()
        var remainingCalls = coinNames.size

        // Loop through the coin names and fetch each one from Firebase
        for (coin in coinNames) {
            try {
                val snapshot = database
                    .child("Coins")
                    .child("Asset")
                    .child(coin)
                    .get()
                    .await() // Use await to suspend the function until the data is fetched

                val firebaseCoin = snapshot.getValue(Firebase_Coin::class.java)
                firebaseCoin?.let {
                    list.add(it)
                }
            } catch (e: Exception) {
                Log.e("FirebaseRepository", "Error fetching coin: $coin", e)
            } finally {
                remainingCalls--

                // Once all calls are finished, call the onResult callback with the list
                if (remainingCalls == 0) {
                    onResult(list)
                }
            }
        }
    }
}


