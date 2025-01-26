package com.example.coinbnx.Dpi

import com.example.coinbnx.Api.CoinApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v2/") // API base URL
            .addConverterFactory(GsonConverterFactory.create()) // JSON to Kotlin data class conversion
            .build() // Retrofit automatically uses OkHttpClient
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoinApi {
        return retrofit.create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
}
