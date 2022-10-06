package com.plcoding.cryptocurrencyapp.di

import com.plcoding.cryptocurrencyapp.common.Constants
import com.plcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//dependency injection - it helps us to make our dependencies replaceable (db, api instance for example)
//we want avoid hardcoding these dependencies into objects - it is bad for testing
@Module
//singleton component - all dependencies in the module live as long as the app does
@InstallIn(SingletonComponent::class)
object AppModule {

    //function provides dependency
    @Provides
    //to make sure we only have single instance of whatever the function returns
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            //for serializing and deserializing json data
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}